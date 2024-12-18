package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.GrupoComissao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class GrupoComissaoDAO {

	public GrupoComissao obter(GrupoComissao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT OCOG.\"GroupCode\" AS ID, OCOG.\"GroupName\" AS DESCRICAO FROM " + model.getEmpresa().getDbInstancia() + ".OCOG WHERE OCOG.\"GroupCode\" = ?", model.getId());
	
		return (GrupoComissao) broker.getObjectBean(GrupoComissao.class, "id", "descricao"); 
		
	}

}
