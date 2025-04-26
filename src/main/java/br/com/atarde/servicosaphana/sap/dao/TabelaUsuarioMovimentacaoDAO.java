package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.sap.hana.model.TabelaUsuarioMovimentacaoModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class TabelaUsuarioMovimentacaoDAO {
	
	public TabelaUsuarioMovimentacaoModel obterUltimo(TabelaUsuarioMovimentacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT MAX(TO_INTEGER(MOV.\"Code\")) FROM " + model.getEmpresa().getDbInstancia() + ".\"@ATRD_MOVVDA\" MOV ");
		
		return (TabelaUsuarioMovimentacaoModel) broker.getObjectBean(TabelaUsuarioMovimentacaoModel.class, "id");
		
	}	

}
