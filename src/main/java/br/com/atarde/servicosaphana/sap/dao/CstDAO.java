package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.CST;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CstDAO {

	public CST obterPeloCodigo(CST model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OTSC.\"CodeOut\" FROM" + model.getEmpresa().getDbInstancia() + ".OTSC WHERE OTSC.\"CodeOut\"= ? AND OTSC.\"Category\" = ?", model.getCodigo(), model.getCategoria().getId());

		return (CST) broker.getObjectBean(CST.class, "codigo");
	}

}
