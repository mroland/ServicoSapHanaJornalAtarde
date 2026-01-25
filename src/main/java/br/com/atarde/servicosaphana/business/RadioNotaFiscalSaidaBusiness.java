package br.com.atarde.servicosaphana.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.HistoricoRadioNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.RadioNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.RadioNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.RadioNotaFiscalSaidaParcelaDAO;
import br.com.atarde.servicosaphana.dao.TabelaUsuarioImpostoTaxaDAO;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaLinhaImposto;
import br.com.atarde.servicosaphana.model.HistoricoRadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinhaImposto;
import br.com.atarde.servicosaphana.sap.business.service.RadioNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.dao.NotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class RadioNotaFiscalSaidaBusiness extends NotaFiscalSaidaBusinessAB {

	public void inserirSAP(Empresa model) {

		List<RadioNotaFiscalSaida> lista = new ArrayList<RadioNotaFiscalSaida>();

		for (RadioNotaFiscalSaida item : new RadioNotaFiscalSaidaDAO().pesquisarInterface(new RadioNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new RadioNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setParcelas(new RadioNotaFiscalSaidaParcelaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new RadioNotaFiscalSaidaDAO().alterarInterface(item);

				lista.add(item);

			} catch (TSApplicationException e) {

				item.setStatus(new Status(3L));

				item.setDataImportacao(new Timestamp(System.currentTimeMillis()));

				if (!TSUtil.isEmpty(e.getMessage())) {

					item.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

				} else {

					item.setMensagemErro("erro Banco");

				}

				try {

					new HistoricoRadioNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new RadioNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (RadioNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(RadioNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getAnunciante().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getAnunciante());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			this.obterSequenciaDefaultParceiroNegocio(model);

			this.setarImpostoCasoNecessario(model);

			NotaFiscalSaida nff = new NotaFiscalSaidaDAO().obterIdExterno(model);
			if (TSUtil.isEmpty(nff)) {

				new RadioNotaFiscalSaidaSapBusinessService().inserir(model);
				model.setFlagDocumentoExistente(false);

			} else {

				model.setSapDocumentoId(nff.getId());
				model.setFlagDocumentoExistente(true);

			}

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoRadioNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new RadioNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoRadioNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new RadioNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private void setarImpostoCasoNecessario(RadioNotaFiscalSaida model) {

		boolean retorno;

		retorno = this.setarImposto(Constantes.CODIGO_IMPOSTO_CLIENTE, model);

		if (!retorno) {

			retorno = this.setarImposto(Constantes.CODIGO_IMPOSTO_GERAL, model);

		}

		if (retorno) {

			this.ajustarParcelas(model);

		}
	}

	private void ajustarParcelas(RadioNotaFiscalSaida model) {

		BigDecimal valorTotalImposto = BigDecimal.ZERO;
		BigDecimal valorTotalParcelas = BigDecimal.ZERO;
		BigDecimal valorRateadoPorParcela = BigDecimal.ZERO;
		BigDecimal valorTotalNff = BigDecimal.ZERO;

		if (!TSUtil.isEmpty(model.getParcelas())) {

			int qtdParcelas = model.getParcelas().size();

			for (RadioNotaFiscalSaidaLinha linha : model.getLinhas()) {

				valorTotalNff = valorTotalNff.add(linha.getValor());

				for (RadioNotaFiscalSaidaLinhaImposto imposto : linha.getImpostos()) {

					valorTotalImposto = valorTotalImposto.add(imposto.getValor());

				}

			}

			valorRateadoPorParcela = valorTotalImposto.divide(new BigDecimal(qtdParcelas), 2, RoundingMode.HALF_UP);

			for (ParcelaAB parcela : model.getParcelas()) {

				parcela.setValorSemImpostoRetido(parcela.getValor().subtract(valorRateadoPorParcela));

				valorTotalParcelas = valorTotalParcelas.add(parcela.getValorSemImpostoRetido());

			}

			if (valorTotalNff.subtract(valorTotalImposto).compareTo(valorTotalParcelas) > 0) {

				model.getParcelas().get(qtdParcelas - 1).setValorSemImpostoRetido(model.getParcelas().get(qtdParcelas - 1).getValorSemImpostoRetido().add((valorTotalNff.subtract(valorTotalImposto)).subtract(valorTotalParcelas)));

			}
			if (valorTotalNff.subtract(valorTotalImposto).compareTo(valorTotalParcelas) < 0) {

				model.getParcelas().get(qtdParcelas - 1).setValorSemImpostoRetido(model.getParcelas().get(qtdParcelas - 1).getValorSemImpostoRetido().subtract(valorTotalParcelas.subtract((valorTotalNff.subtract(valorTotalImposto)))));

			}

		}

	}

	private boolean setarImposto(Long codigoImpostoCliente, RadioNotaFiscalSaida model) {

		boolean encontrou = false;

		List<TabelaUsuarioImpostoTaxa> taxas;

		if (codigoImpostoCliente.equals(Constantes.CODIGO_IMPOSTO_CLIENTE)) {

			taxas = new TabelaUsuarioImpostoTaxaDAO().obterPorCliente(model.getEmpresa(), model.getFilial(), model.getCliente().getId(), codigoImpostoCliente);

		} else {

			taxas = new TabelaUsuarioImpostoTaxaDAO().obterPorGeral(model.getEmpresa(), model.getFilial(), model.getCliente().getEndereco().getMunicipio().getId(), model.getCliente().getClassificacao().getId(), codigoImpostoCliente, model.getSequencia().getId());

			if (TSUtil.isEmpty(taxas)) {

				taxas = new TabelaUsuarioImpostoTaxaDAO().obterPorGeral(model.getEmpresa(), model.getFilial(), Constantes.MUNICIPIO_OUTROS, model.getCliente().getClassificacao().getId(), codigoImpostoCliente, model.getSequencia().getId());

			}

		}

		if (!TSUtil.isEmpty(taxas)) {

			encontrou = true;

			for (RadioNotaFiscalSaidaLinha linha : model.getLinhas()) {

				this.setarTaxas(linha, taxas);

			}

		}

		return encontrou;
	}

	private void setarTaxas(RadioNotaFiscalSaidaLinha linha, List<TabelaUsuarioImpostoTaxa> taxas) {

		RadioNotaFiscalSaidaLinhaImposto imposto;

		if (TSUtil.isEmpty(linha.getImpostos())) {

			linha.setImpostos(new ArrayList<RadioNotaFiscalSaidaLinhaImposto>());

		}

		for (TabelaUsuarioImpostoTaxa taxa : taxas) {

			imposto = new RadioNotaFiscalSaidaLinhaImposto();

			imposto.setImpostoId(taxa.getImpostoId());

			imposto.setPercentual(taxa.getPercentual());

			imposto.setValor(linha.getValor().multiply(taxa.getPercentual()).divide(new BigDecimal(100L), 2, RoundingMode.HALF_UP));

			linha.getImpostos().add(imposto);

		}
	}

	private HistoricoRadioNotaFiscalSaida carregaHistorico(RadioNotaFiscalSaida model) {

		HistoricoRadioNotaFiscalSaida nota = new HistoricoRadioNotaFiscalSaida();

		nota.setInterfaceOriginalId(model.getInterfaceId());

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setCliente(model.getCliente());

		nota.setAnunciante(model.getAnunciante());

		nota.setCondicaoPagamento(model.getCondicaoPagamento());

		nota.setCriadoPor(model.getCriadoPor());

		nota.setDataAtualizacao(model.getDataAtualizacao());

		nota.setDataCriacao(model.getDataCriacao());

		nota.setDataDocumento(model.getDataDocumento());

		nota.setDataExportacao(model.getDataExportacao());

		nota.setDataImportacao(new Date());

		nota.setDataLancamento(model.getDataLancamento());

		nota.setDataVencimento(model.getDataVencimento());

		nota.setEmpresa(model.getEmpresa());

		nota.setId(model.getId());

		nota.setIdExterno(model.getIdExterno());

		nota.setInterfaceId(model.getInterfaceId());

		nota.setLinhas(model.getLinhas());

		nota.setMensagemErro(model.getMensagemErro());

		nota.setObservacao(model.getObservacao());

		nota.setOrigem(model.getOrigem());

		nota.setParcela(model.getParcela());

		nota.setParcelas(model.getParcelas());

		nota.setPedidoVenda(model.getPedidoVenda());

		nota.setPercentualDesconto(model.getPercentualDesconto());

		nota.setSequencia(model.getSequencia());

		nota.setSerial(model.getSerial());

		nota.setStatus(model.getStatus());

		nota.setUAutorizacaoPublicidade(model.getUAutorizacaoPublicidade());

		nota.setUComissaoAgencia(model.getUComissaoAgencia());

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setUNumeroPI(model.getUNumeroPI());

		nota.setUDataContrato(model.getUDataContrato());

		nota.setUPeriodoVeiculacao(model.getUPeriodoVeiculacao());

		nota.setUTipoTransacao(model.getUTipoTransacao());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setUEntregaVendedor(model.getUEntregaVendedor());

		nota.setUDataPublicacaoInicial(model.getUDataPublicacaoInicial());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		nota.setFilial(model.getFilial());

		nota.setArquivoRemessa(model.getArquivoRemessa());

		nota.setFlagDocumentoExistente(model.isFlagDocumentoExistente());

		nota.setSapDocumentoId(model.getSapDocumentoId());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (RadioNotaFiscalSaida item : new RadioNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new RadioNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new RadioNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
