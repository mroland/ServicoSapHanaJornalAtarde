package br.com.atarde.servicosaphana.business;

import java.util.List;

import br.com.atarde.servicosaphana.dao.VendaVulsaNotaFiscalSaidaMovimentacaoDAO;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaVulsaNotaFiscalSaidaMovimentacao;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;

public class TabelaUsuarioMovimentacaoBusiness {

	public List<VendaVulsaNotaFiscalSaidaMovimentacao> pesquisar(NotaFiscalSaidaAB model) {

		return new VendaVulsaNotaFiscalSaidaMovimentacaoDAO().pesquisar(new VendaVulsaNotaFiscalSaidaMovimentacao((VendaAvulsaNotaFiscalSaida) model));
		
	}

}
