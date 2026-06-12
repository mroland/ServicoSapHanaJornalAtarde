package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.AssinaturaPedidoVendaDAO;
import br.com.atarde.servicosaphana.dao.AssinaturaPedidoVendaLinhaDAO;
import br.com.atarde.servicosaphana.dao.AssinaturaPedidoVendaParcelaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoAssinaturaPedidoVendaDAO;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.model.HistoricoAssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.sap.business.service.AssinaturaPedidoVendaSapBusinessService;
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

public class AssinaturaPedidoVendaBusiness extends PedidoVendaBusinessAB {

	public void inserirSAP(Empresa model) {

		List<AssinaturaPedidoVenda> lista = new ArrayList<AssinaturaPedidoVenda>();

		for (AssinaturaPedidoVenda item : new AssinaturaPedidoVendaDAO().pesquisarInterface(new AssinaturaPedidoVenda(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new AssinaturaPedidoVendaLinhaDAO().pesquisarInterface(item));

				item.setParcelas(new AssinaturaPedidoVendaParcelaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new AssinaturaPedidoVendaDAO().alterarInterface(item);

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

					new HistoricoAssinaturaPedidoVendaDAO().inserirInterface(this.carregaHistorico(item));

					new AssinaturaPedidoVendaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (AssinaturaPedidoVenda item : lista) {

			this.inserir(item);

		}

	}

	public PedidoVendaAB inserir(AssinaturaPedidoVenda model) {

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

					new AssinaturaPedidoVendaSapBusinessService().inserir((AssinaturaPedidoVenda) model);
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

			new HistoricoAssinaturaPedidoVendaDAO().inserirInterface(this.carregaHistorico(model));

			new AssinaturaPedidoVendaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoAssinaturaPedidoVendaDAO().inserirInterface(this.carregaHistorico(model));

				new AssinaturaPedidoVendaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoAssinaturaPedidoVenda carregaHistorico(AssinaturaPedidoVenda model) {

		HistoricoAssinaturaPedidoVenda nota = new HistoricoAssinaturaPedidoVenda();

		nota.setInterfaceOriginalId(model.getInterfaceId());

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setCliente(model.getCliente());

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

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setUObservacao(model.getUObservacao());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		nota.setFilial(model.getFilial());

		nota.setFlagRemessa(model.getFlagRemessa());

		nota.setArquivoRemessa(model.getArquivoRemessa());
		
		nota.setFlagDocumentoExistente(model.isFlagDocumentoExistente());

		nota.setSapDocumentoId(model.getSapDocumentoId());

		nota.setFlagNotaFiscalSaida(model.getFlagNotaFiscalSaida());
		
		nota.setArquivoRemessaSap(model.getArquivoRemessaSap());
		
		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (AssinaturaPedidoVenda item : new AssinaturaPedidoVendaDAO().pesquisarPorAtrasadaInterface(new AssinaturaPedidoVenda(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new AssinaturaPedidoVendaDAO().alterarInterface(item);

		}

	}
}
