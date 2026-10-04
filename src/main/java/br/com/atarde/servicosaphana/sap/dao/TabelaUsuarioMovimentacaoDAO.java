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

	public TabelaUsuarioMovimentacaoModel obterPorChave(TabelaUsuarioMovimentacao model, Long docEntry) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT MIN(TO_INTEGER(MOV.\"Code\")) FROM " + model.getEmpresa().getDbInstancia() + ".\"@ATRD_MOVVDA\" MOV WHERE MOV.\"U_NumDoc\" = ? AND MOV.\"U_ItemCode\" = ? AND MOV.\"U_RefVda\" = ? AND MOV.\"U_TipMov\" = ? ", docEntry, model.getItem().getId(), model.getIdExterno(), model.getTipoMovimentacao());

		return (TabelaUsuarioMovimentacaoModel) broker.getObjectBean(TabelaUsuarioMovimentacaoModel.class, "id");

	}

}
