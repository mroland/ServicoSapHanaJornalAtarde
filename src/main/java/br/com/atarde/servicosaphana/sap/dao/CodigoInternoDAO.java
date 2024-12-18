package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.CodigoInterno;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CodigoInternoDAO {

	public CodigoInterno obter(CodigoInterno model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT CODE, NAME FROM \"@CODIGOINTERO\" WITH(NOLOCK) WHERE CODE = ?", model.getId());

		return (CodigoInterno) broker.getObjectBean(CodigoInterno.class, "id", "descricao");
	}

	
}
