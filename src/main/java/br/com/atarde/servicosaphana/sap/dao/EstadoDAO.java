package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Estado;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EstadoDAO {

	public Estado obter(Estado model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCST.\"Code\" AS ID FROM " + model.getEmpresa().getDbInstancia() + ".OCST WHERE OCST.\"Code\" = ?", model.getId());

		return (Estado) broker.getObjectBean(Estado.class, "id");

	}

}
