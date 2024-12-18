package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.CFOP;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CfopDAO {

	public CFOP obterPeloCodigo(CFOP model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCFP.\"Code\" CODE FROM " + model.getEmpresa().getDbInstancia() + ".OCFP WHERE OCFP.\"Code\" = ?", model.getCodigo());

		return (CFOP) broker.getObjectBean(CFOP.class, "codigo");

	}

}
