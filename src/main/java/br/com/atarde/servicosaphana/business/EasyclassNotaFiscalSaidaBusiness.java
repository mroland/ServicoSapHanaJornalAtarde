package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.EasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.EasyclassNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoEasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.HistoricoEasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.business.service.EasyclassNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class EasyclassNotaFiscalSaidaBusiness extends NotaFiscalSaidaBusinessAB{

	public void inserirSAP(Empresa model) {

		List<EasyclassNotaFiscalSaida> lista = new ArrayList<EasyclassNotaFiscalSaida>();

		for (EasyclassNotaFiscalSaida item : new EasyclassNotaFiscalSaidaDAO().pesquisarInterface(new EasyclassNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new EasyclassNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new EasyclassNotaFiscalSaidaDAO().alterarInterface(item);

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

					new HistoricoEasyclassNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new EasyclassNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (EasyclassNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(EasyclassNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getAnunciante().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getAnunciante());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			this.obterSequenciaDefaultParceiroNegocio(model);

			new EasyclassNotaFiscalSaidaSapBusinessService().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoEasyclassNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new EasyclassNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoEasyclassNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new EasyclassNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoEasyclassNotaFiscalSaida carregaHistorico(EasyclassNotaFiscalSaida model) {

		HistoricoEasyclassNotaFiscalSaida nota = new HistoricoEasyclassNotaFiscalSaida();
		
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

		for (EasyclassNotaFiscalSaida item : new EasyclassNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new EasyclassNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new EasyclassNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
