package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.dao.ClassificadosExecucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.ClassificadosExecucaoNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoClassificadosExecucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.HistoricoClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.business.service.ClassificadosExecucaoNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.dao.SequenciaDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.Sequencia;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class ClassificadosExecucaoNotaFiscalSaidaBusiness {

	public void inserirSAP(Empresa model) {

		List<ClassificadosExecucaoNotaFiscalSaida> lista = new ArrayList<ClassificadosExecucaoNotaFiscalSaida>();

		for (ClassificadosExecucaoNotaFiscalSaida item : new ClassificadosExecucaoNotaFiscalSaidaDAO().pesquisarInterface(new ClassificadosExecucaoNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new ClassificadosExecucaoNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new ClassificadosExecucaoNotaFiscalSaidaDAO().alterarInterface(item);

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

					new ClassificadosExecucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new ClassificadosExecucaoNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (ClassificadosExecucaoNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(ClassificadosExecucaoNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			this.obterSequenciaDefaultParceiroNegocio(model);

			new ClassificadosExecucaoNotaFiscalSaidaSapBusinessService().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoClassificadosExecucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new ClassificadosExecucaoNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoClassificadosExecucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new ClassificadosExecucaoNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private void obterSequenciaDefaultParceiroNegocio(ClassificadosExecucaoNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		Sequencia sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial());

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}

	private HistoricoClassificadosExecucaoNotaFiscalSaida carregaHistorico(ClassificadosExecucaoNotaFiscalSaida model) {

		HistoricoClassificadosExecucaoNotaFiscalSaida nota = new HistoricoClassificadosExecucaoNotaFiscalSaida();

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setCliente(model.getCliente());

		nota.setAnunciante(model.getAnunciante());

		nota.setCondicaoPagamento(model.getCondicaoPagamento());

		nota.setCriadoPor(model.getCriadoPor());

		nota.setDataAtualizacao(model.getDataAtualizacao());

		nota.setDataCriacao(model.getDataCriacao());

		nota.setDataDocumento(model.getDataDocumento());

		nota.setDataExportacao(model.getDataExportacao());

		nota.setDataImportacao(model.getDataImportacao());

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

		nota.setUDataPublicacaoFinal(model.getUDataPublicacaoFinal());

		nota.setUDiasPublicacao(model.getUDiasPublicacao());

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setUFormato(model.getUFormato());

		nota.setUNumeroPI(model.getUNumeroPI());

		nota.setUPageViews(model.getUPageViews());

		nota.setUPeriodo(model.getUPeriodo());

		nota.setUTipoTransacao(model.getUTipoTransacao());

		nota.setUTituloPublicacao(model.getUTituloPublicacao());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setUEntregaVendedor(model.getUEntregaVendedor());

		nota.setUProduto(model.getUProduto());

		nota.setUCampanha(model.getUCampanha());

		nota.setUDataPublicacaoInicial(model.getUDataPublicacaoInicial());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());
		
		nota.setFilial(model.getFilial());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (ClassificadosExecucaoNotaFiscalSaida item : new ClassificadosExecucaoNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new ClassificadosExecucaoNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new ClassificadosExecucaoNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
