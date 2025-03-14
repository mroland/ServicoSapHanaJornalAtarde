package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.HistoricoVendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.VendaAvulsaNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.VendaAvulsaNotaFiscalSaidaRomaneioDAO;
import br.com.atarde.servicosaphana.model.HistoricoVendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.business.service.VendaAvulsaNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class VendaAvulsaNotaFiscalSaidaBusiness extends NotaFiscalSaidaBusinessAB {

	public void inserirSAP(Empresa model) {

		List<VendaAvulsaNotaFiscalSaida> lista = new ArrayList<VendaAvulsaNotaFiscalSaida>();

		for (VendaAvulsaNotaFiscalSaida item : new VendaAvulsaNotaFiscalSaidaDAO().pesquisarInterface(new VendaAvulsaNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new VendaAvulsaNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setRomaneios(new VendaAvulsaNotaFiscalSaidaRomaneioDAO().pesquisarInterface(item));

				item.setTransferenciaEstoqueReferencia(new TransferenciaEstoqueBusiness().obterInterface(new NotaFiscalSaida(item.getInterfaceId(), item.getOrigem())));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				if (TSUtil.isEmpty(item.getTransferenciaEstoqueReferencia()) || (!TSUtil.isEmpty(item.getTransferenciaEstoqueReferencia().getId()) && item.getTransferenciaEstoqueReferencia().getStatus().getId().equals(1L))) {

					new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

					lista.add(item);

				}

			} catch (TSApplicationException e) {

				item.setStatus(new Status(3L));

				item.setDataImportacao(new Timestamp(System.currentTimeMillis()));

				if (!TSUtil.isEmpty(e.getMessage())) {

					item.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

				} else {

					item.setMensagemErro("erro Banco");

				}

				try {

					new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (VendaAvulsaNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(VendaAvulsaNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			if (!model.getFlagConsignado()) {

				this.obterSequenciaDefaultParceiroNegocio(model);

			}

			new VendaAvulsaNotaFiscalSaidaSapBusinessService().inserir((VendaAvulsaNotaFiscalSaida) model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new VendaAvulsaNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoVendaAvulsaNotaFiscalSaida carregaHistorico(VendaAvulsaNotaFiscalSaida model) {

		HistoricoVendaAvulsaNotaFiscalSaida nota = new HistoricoVendaAvulsaNotaFiscalSaida();

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

		nota.setPedidoVenda(model.getPedidoVenda());

		nota.setPercentualDesconto(model.getPercentualDesconto());

		nota.setRomaneios(model.getRomaneios());

		nota.setSequencia(model.getSequencia());

		nota.setSerial(model.getSerial());

		nota.setStatus(model.getStatus());

		nota.setUBanca(model.getUBanca());

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setULote(model.getULote());

		nota.setUObservacao(model.getUObservacao());

		nota.setURdj(model.getURdj());

		nota.setUTipoBanca(model.getUTipoBanca());

		nota.setUTipoFaturamento(model.getUTipoFaturamento());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		nota.setFilial(model.getFilial());

		nota.setFlagConsignado(model.getFlagConsignado());

		if (!TSUtil.isEmpty(model.getTransferenciaEstoqueReferencia())) {

			nota.setTransferenciaEstoqueReferencia(model.getTransferenciaEstoqueReferencia());

			nota.getTransferenciaEstoqueReferencia().setDataImportacao(nota.getDataImportacao());

			nota.getTransferenciaEstoqueReferencia().setStatus(nota.getStatus());

			nota.getTransferenciaEstoqueReferencia().setMensagemErro(nota.getMensagemErro());

			nota.getTransferenciaEstoqueReferencia().setDataAtualizacao(nota.getDataAtualizacao());

		}

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (VendaAvulsaNotaFiscalSaida item : new VendaAvulsaNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new VendaAvulsaNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
