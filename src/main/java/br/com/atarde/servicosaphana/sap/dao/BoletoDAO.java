package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Boleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class BoletoDAO {
	
	public Boleto obter(Boleto model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT OBOE.\"BoeNum\", OBOE.\"BoeStatus\" FROM " + model.getEmpresa().getDbInstancia() + ".OBOE WHERE OBOE.\"BoeNum\" = ?", model.getId());

		return (Boleto) broker.getObjectBean(Boleto.class, "id", "statusBoleto.id");
	}

}
