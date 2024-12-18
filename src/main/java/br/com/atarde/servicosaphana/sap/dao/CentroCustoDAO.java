package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.CentroCusto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CentroCustoDAO {

	public CentroCusto obter(CentroCusto model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OOCR.\"OcrCode\" OCRCODE AS ID FROM " + model.getEmpresa().getDbInstancia() + ".OOCR WHERE OOCR.\"DimCode\" = ? AND OOCR.\"OcrCode\" = ?", model.getDimensao().getId(), model.getId());

		return (CentroCusto) broker.getObjectBean(CentroCusto.class, "id");
	}

}
