package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.HistoricoTransferenciaEstoqueDAO;
import br.com.atarde.servicosaphana.dao.TransferenciaEstoqueDAO;
import br.com.atarde.servicosaphana.model.HistoricoTransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.sap.business.service.TransferenciaEstoqueSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueBusiness {

	public TransferenciaEstoque obterInterface(NotaFiscalSaidaAB model) {

		TransferenciaEstoque transferenciaModel = null;
		if (model instanceof NotaFiscalSaida) {

			transferenciaModel = new TransferenciaEstoque((NotaFiscalSaida) model);

		} else if (model instanceof DevolucaoNotaFiscalSaida) {

			transferenciaModel = new TransferenciaEstoque((DevolucaoNotaFiscalSaida) model);

		}

		TransferenciaEstoque transferencia = new TransferenciaEstoqueDAO().obter(transferenciaModel);

		if (!TSUtil.isEmpty(transferencia)) {

			transferencia.setLinhas(new TransferenciaEstoqueDAO().pesquisarLinhas(transferencia));

		}

		return transferencia;
	}

	public void inserirSAP(Empresa model) {

		List<TransferenciaEstoque> lista = new ArrayList<TransferenciaEstoque>();

		for (TransferenciaEstoque item : new TransferenciaEstoqueDAO().pesquisarInterface(new TransferenciaEstoque(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new TransferenciaEstoqueDAO().pesquisarLinhas(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new TransferenciaEstoqueDAO().alterarInterface(item);

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

					new HistoricoTransferenciaEstoqueDAO().inserirInterface(this.carregaHistorico(item));

					new TransferenciaEstoqueDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (TransferenciaEstoque item : lista) {

			this.inserir(item);

		}

	}

	public TransferenciaEstoque inserir(TransferenciaEstoque model) {

		try {

			new TransferenciaEstoqueSapBusinessService().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			model.setDataImportacao(new Date());

			new HistoricoTransferenciaEstoqueDAO().inserirInterface(this.carregaHistorico(model));

			if ((!TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada().getInterfaceId())) || (!TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId()))) {

				new TransferenciaEstoqueDAO().alterarInterface(model);

			} else {

				new TransferenciaEstoqueDAO().excluirInterface(model);

			}

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoTransferenciaEstoqueDAO().inserirInterface(this.carregaHistorico(model));

				new TransferenciaEstoqueDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoTransferenciaEstoque carregaHistorico(TransferenciaEstoque model) {

		HistoricoTransferenciaEstoque nota = new HistoricoTransferenciaEstoque();

		nota.setInterfaceOriginalId(model.getInterfaceId());

		nota.setAtualizadoPor(model.getAtualizadoPor());

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

		nota.setStatus(model.getStatus());

		nota.setFilial(model.getFilial());

		nota.setEstoqueDestino(model.getEstoqueDestino());

		nota.setEstoqueOrigem(model.getEstoqueOrigem());

		nota.setNotaFiscalSaidaReferenciada(model.getNotaFiscalSaidaReferenciada());

		nota.setDevolucaoNotaFiscalSaidaReferenciada(model.getDevolucaoNotaFiscalSaidaReferenciada());

		nota.setObservacaoDiario(model.getObservacaoDiario());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (TransferenciaEstoque item : new TransferenciaEstoqueDAO().pesquisarPorAtrasadaInterface(new TransferenciaEstoque(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new TransferenciaEstoqueDAO().alterarInterface(item);

		}
	}

}
