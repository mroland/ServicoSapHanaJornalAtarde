package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.ClassificadosExecucaoPedidoVendaDAO;
import br.com.atarde.servicosaphana.dao.ClassificadosExecucaoPedidoVendaLinhaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoClassificadosExecucaoPedidoVendaDAO;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVenda;
import br.com.atarde.servicosaphana.model.HistoricoClassificadosExecucaoPedidoVenda;
import br.com.atarde.servicosaphana.sap.business.service.ClassificadosExecucaoPedidoVendaSapBusinessService;
import br.com.atarde.servicosaphana.sap.dao.NotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.sap.dao.PedidoVendaDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class ClassificadosExecucaoPedidoVendaBusiness extends PedidoVendaBusinessAB {

	public void inserirSAP(Empresa model) {

		List<ClassificadosExecucaoPedidoVenda> lista = new ArrayList<ClassificadosExecucaoPedidoVenda>();

		for (ClassificadosExecucaoPedidoVenda item : new ClassificadosExecucaoPedidoVendaDAO().pesquisarInterface(new ClassificadosExecucaoPedidoVenda(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new ClassificadosExecucaoPedidoVendaLinhaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new ClassificadosExecucaoPedidoVendaDAO().alterarInterface(item);

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

					new HistoricoClassificadosExecucaoPedidoVendaDAO().inserirInterface(this.carregaHistorico(item));

					new ClassificadosExecucaoPedidoVendaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (ClassificadosExecucaoPedidoVenda item : lista) {

			this.inserir(item);

		}

	}

	public PedidoVendaAB inserir(ClassificadosExecucaoPedidoVenda model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			NotaFiscalSaida saida = new NotaFiscalSaida(model.getEmpresa());
			saida.setOrigem(model.getOrigem());
			saida.setIdExterno(model.getIdExterno());

			saida = new NotaFiscalSaidaDAO().obterIdExterno(saida);
			if (TSUtil.isEmpty(saida)) {

				PedidoVenda pedido = new PedidoVendaDAO().obterIdExterno(model);

				if (!TSUtil.isEmpty(pedido)) {

					model.setSapDocumentoId(pedido.getId());
					model.setFlagDocumentoExistente(true);

				} else {

					new ClassificadosExecucaoPedidoVendaSapBusinessService().inserir(model);
					model.setFlagDocumentoExistente(false);

				}

				model.setFlagNotaFiscalSaida(false);

			} else {

				model.setSapDocumentoId(saida.getId());
				model.setFlagDocumentoExistente(true);
				model.setFlagNotaFiscalSaida(true);

			}

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoClassificadosExecucaoPedidoVendaDAO().inserirInterface(this.carregaHistorico(model));

			new ClassificadosExecucaoPedidoVendaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoClassificadosExecucaoPedidoVendaDAO().inserirInterface(this.carregaHistorico(model));

				new ClassificadosExecucaoPedidoVendaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoClassificadosExecucaoPedidoVenda carregaHistorico(ClassificadosExecucaoPedidoVenda model) {

		HistoricoClassificadosExecucaoPedidoVenda nota = new HistoricoClassificadosExecucaoPedidoVenda();

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

		nota.setArquivoRemessa(model.getArquivoRemessa());

		nota.setFlagDocumentoExistente(model.isFlagDocumentoExistente());

		nota.setSapDocumentoId(model.getSapDocumentoId());

		nota.setFlagNotaFiscalSaida(model.getFlagNotaFiscalSaida());
		
		nota.setArquivoRemessaSap(model.getArquivoRemessaSap());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (ClassificadosExecucaoPedidoVenda item : new ClassificadosExecucaoPedidoVendaDAO().pesquisarPorAtrasadaInterface(new ClassificadosExecucaoPedidoVenda(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new ClassificadosExecucaoPedidoVendaDAO().alterarInterface(item);

		}

	}
}
