package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Utilizacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class UtilizacaoDAO {

	public Utilizacao obter(Utilizacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OUSG.ID FROM " + model.getEmpresa().getDbInstancia() + ".OUSG WHERE OUSG.ID = ?", model.getId());

		return (Utilizacao) broker.getObjectBean(Utilizacao.class, "id");
	}

}
