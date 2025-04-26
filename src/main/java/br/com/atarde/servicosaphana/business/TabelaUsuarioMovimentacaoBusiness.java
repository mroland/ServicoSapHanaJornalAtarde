package br.com.atarde.servicosaphana.business;

import java.util.List;

import br.com.atarde.servicosaphana.dao.TabelaUsuarioMovimentacaoDAO;
import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;

public class TabelaUsuarioMovimentacaoBusiness {

	public List<TabelaUsuarioMovimentacao> pesquisar(NotaFiscalSaidaAB model) {

		if (model instanceof NotaFiscalSaida) {

			return new TabelaUsuarioMovimentacaoDAO().pesquisar(new TabelaUsuarioMovimentacao((NotaFiscalSaida) model));

		} else if (model instanceof DevolucaoNotaFiscalSaida) {

			return new TabelaUsuarioMovimentacaoDAO().pesquisar(new TabelaUsuarioMovimentacao((DevolucaoNotaFiscalSaida) model));

		}

		return null;

	}

}
