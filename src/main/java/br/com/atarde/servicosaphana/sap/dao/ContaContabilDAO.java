package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.ContaContabil;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ContaContabilDAO {

	public ContaContabil obter(ContaContabil model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT OACT.\"AcctCode\" AS ID FROM" + model.getEmpresa().getDbInstancia() + ".OACT WHERE OACT.\"AcctCode\" = ?", model.getId());
		
		return (ContaContabil) broker.getObjectBean(ContaContabil.class, "id");
	
	}

}
