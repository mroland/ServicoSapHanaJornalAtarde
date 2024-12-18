package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.CodigoImposto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CodigoImpostoDAO {

	public CodigoImposto obter(CodigoImposto model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OSTC.\"Code\" FROM " + model.getEmpresa().getDbInstancia() + ".OSTC WHERE OSTC.\"Code\" = ?", model.getId());

		return (CodigoImposto) broker.getObjectBean(CodigoImposto.class, "id");

	}

}
