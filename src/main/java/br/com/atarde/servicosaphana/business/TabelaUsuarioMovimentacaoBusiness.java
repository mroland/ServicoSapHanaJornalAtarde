package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.HistoricoTabelaUsuarioMovimentacaoDAO;
import br.com.atarde.servicosaphana.dao.TabelaUsuarioMovimentacaoDAO;
import br.com.atarde.servicosaphana.model.HistoricoTabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.sap.business.service.TabelaUsuarioMovimentacaoSapBusinessService;
import br.com.atarde.servicosaphana.sap.hana.model.TabelaUsuarioMovimentacaoModel;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoBusiness {

	public List<TabelaUsuarioMovimentacao> pesquisarInterface(NotaFiscalSaidaAB model) {

		if (model instanceof NotaFiscalSaida) {

			return new TabelaUsuarioMovimentacaoDAO().pesquisarInterface(new TabelaUsuarioMovimentacao((NotaFiscalSaida) model));

		} else if (model instanceof DevolucaoNotaFiscalSaida) {

			return new TabelaUsuarioMovimentacaoDAO().pesquisarInterface(new TabelaUsuarioMovimentacao((DevolucaoNotaFiscalSaida) model));

		}

		return null;

	}

	public void inserirSAP(Empresa model) {

		List<TabelaUsuarioMovimentacao> lista = new ArrayList<TabelaUsuarioMovimentacao>();

		for (TabelaUsuarioMovimentacao item : new TabelaUsuarioMovimentacaoDAO().pesquisarInterface(new TabelaUsuarioMovimentacao(model))) {

			try {

				if (!TSUtil.isEmpty(item.getSapNotaFiscalSaidaId()) || !TSUtil.isEmpty(item.getSapDevolucaoNotaFiscalSaidaId())) {

					item.setStatus(new Status(2L));

					item.setMensagemErro(null);

					new TabelaUsuarioMovimentacaoDAO().alterarInterface(item);

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

					new HistoricoTabelaUsuarioMovimentacaoDAO().inserirInterface(this.carregaHistorico(item));

					new TabelaUsuarioMovimentacaoDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (TabelaUsuarioMovimentacao item : lista) {

			item.setEmpresa(model);

			this.inserir(item);

		}

	}

	public TabelaUsuarioMovimentacao inserir(TabelaUsuarioMovimentacao model) {

		try {

			TabelaUsuarioMovimentacaoModel obterUltimo = new br.com.atarde.servicosaphana.sap.dao.TabelaUsuarioMovimentacaoDAO().obterUltimo(model);

			if (TSUtil.isEmpty(obterUltimo) || TSUtil.isEmpty(obterUltimo.getId())) {

				obterUltimo = new TabelaUsuarioMovimentacaoModel();
				obterUltimo.setId(1L);

			}

			model.setId(obterUltimo.getId() + 1);

			new TabelaUsuarioMovimentacaoSapBusinessService().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			model.setDataImportacao(new Date());

			new HistoricoTabelaUsuarioMovimentacaoDAO().inserirInterface(this.carregaHistorico(model));

			new TabelaUsuarioMovimentacaoDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoTabelaUsuarioMovimentacaoDAO().inserirInterface(this.carregaHistorico(model));

				new TabelaUsuarioMovimentacaoDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private HistoricoTabelaUsuarioMovimentacao carregaHistorico(TabelaUsuarioMovimentacao model) {

		HistoricoTabelaUsuarioMovimentacao nota = new HistoricoTabelaUsuarioMovimentacao();

		nota.setInterfaceOriginalId(model.getInterfaceId());

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setDataAtualizacao(model.getDataAtualizacao());

		nota.setDataCriacao(model.getDataCriacao());

		nota.setDataExportacao(model.getDataExportacao());

		nota.setDataImportacao(new Date());

		nota.setEmpresa(model.getEmpresa());

		nota.setId(model.getId());

		nota.setIdExterno(model.getIdExterno());

		nota.setInterfaceId(model.getInterfaceId());

		nota.setMensagemErro(model.getMensagemErro());

		nota.setStatus(model.getStatus());

		nota.setFilial(model.getFilial());

		nota.setArquivoRemessa(model.getArquivoRemessa());

		nota.setDevolucaoNotaFiscalSaidaReferenciada(model.getDevolucaoNotaFiscalSaidaReferenciada());

		nota.setNotaFiscalSaidaReferenciada(model.getNotaFiscalSaidaReferenciada());

		nota.setQuantidade(model.getQuantidade());

		nota.setSapDevolucaoNotaFiscalSaidaId(model.getSapDevolucaoNotaFiscalSaidaId());

		nota.setSapNotaFiscalSaidaId(model.getSapNotaFiscalSaidaId());

		nota.setTipoMovimentacao(model.getTipoMovimentacao());
		
		nota.setItem(model.getItem());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {
		
		for (TabelaUsuarioMovimentacao item : new TabelaUsuarioMovimentacaoDAO().pesquisarPorAtrasadaInterface(new TabelaUsuarioMovimentacao(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new TabelaUsuarioMovimentacaoDAO().alterarInterface(item);

		}		
	}

}
