package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Pais;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class PaisDAO {

	public Pais obter(Pais model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCRY.\"Code\" AS ID FROM " + model.getEmpresa().getDbInstancia() + ".OCRY WHERE OCRY.\"Code\" = ?", model.getId());

		return (Pais) broker.getObjectBean(Pais.class, "id");

	}

}
