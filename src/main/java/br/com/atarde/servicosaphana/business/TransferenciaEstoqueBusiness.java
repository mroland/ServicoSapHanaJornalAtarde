package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.dao.TransferenciaEstoqueDAO;
import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueBusiness {

	public TransferenciaEstoque obterInterface(NotaFiscalSaida model) {

		TransferenciaEstoque transferencia = new TransferenciaEstoqueDAO().obter(new TransferenciaEstoque(model));
		
		if(!TSUtil.isEmpty(transferencia)) {
			
			transferencia.setLinhas(new TransferenciaEstoqueDAO().pesquisarLinhas(transferencia));
			
		}
		
		return transferencia;
	}

}
