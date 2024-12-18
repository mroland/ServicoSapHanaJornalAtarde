package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Origem;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class OrigemDAO {

	public Origem obter(Origem model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT UFD1.\"FldValue\" ID, UFD1.\"Descr\" FROM " + model.getEmpresa().getDbInstancia() + ".UFD1 WHERE UFD1.\"TableID\" = 'ODPI' AND UFD1.\"FieldID\" = 0 AND UFD1.\"FldValue\" = ? ", model.getId());

		return (Origem) broker.getObjectBean(Origem.class, "id", "descricao");

	}

}
