package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.DevolucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.DevolucaoNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoDevolucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.HistoricoDevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.business.service.DevolucaoNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class DevolucaoNotaFiscalSaidaBusiness extends NotaFiscalSaidaBusinessAB {

	public void inserirSAP(Empresa model) {

		List<DevolucaoNotaFiscalSaida> lista = new ArrayList<DevolucaoNotaFiscalSaida>();

		for (DevolucaoNotaFiscalSaida item : new DevolucaoNotaFiscalSaidaDAO().pesquisarInterface(new DevolucaoNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new DevolucaoNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setTransferenciaEstoqueReferencia(new TransferenciaEstoqueBusiness().obterInterface(new DevolucaoNotaFiscalSaida(item.getInterfaceId(), item.getOrigem())));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				if (TSUtil.isEmpty(item.getTransferenciaEstoqueReferencia()) || (!TSUtil.isEmpty(item.getTransferenciaEstoqueReferencia().getId()) && item.getTransferenciaEstoqueReferencia().getStatus().getId().equals(1L))) {

					new DevolucaoNotaFiscalSaidaDAO().alterarInterface(item);

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

					new HistoricoDevolucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new DevolucaoNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (DevolucaoNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public DevolucaoNotaFiscalSaida inserir(DevolucaoNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			if (!model.getFlagConsignado()) {

				this.obterSequenciaDefaultParceiroNegocio(model);

			}

			new DevolucaoNotaFiscalSaidaSapBusinessService().inserir((DevolucaoNotaFiscalSaida) model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoDevolucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new DevolucaoNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoDevolucaoNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new DevolucaoNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoDevolucaoNotaFiscalSaida carregaHistorico(DevolucaoNotaFiscalSaida model) {

		HistoricoDevolucaoNotaFiscalSaida nota = new HistoricoDevolucaoNotaFiscalSaida();

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

		nota.setSequencia(model.getSequencia());

		nota.setSerial(model.getSerial());

		nota.setStatus(model.getStatus());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		nota.setFilial(model.getFilial());

		nota.setFlagConsignado(model.getFlagConsignado());
		
		nota.setArquivoRemessa(model.getArquivoRemessa());

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

		for (DevolucaoNotaFiscalSaida item : new DevolucaoNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new DevolucaoNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new DevolucaoNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
