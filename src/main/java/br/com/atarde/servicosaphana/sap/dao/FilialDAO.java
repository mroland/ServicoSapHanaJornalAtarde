package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.hana.model.FilialModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class FilialDAO {

	@SuppressWarnings("unchecked")
	public List<FilialModel> pesquisar(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getJndi());

		broker.setSQL("SELECT OBPL.\"BPLId\" id, OBPL.\"BPLName\" descricao, OBPL.\"MainBPL\" as flag_principal FROM " + model.getDbInstancia() + ".OBPL WHERE OBPL.\"Disabled\" = 'N'");

		return broker.getCollectionBean(FilialModel.class, "id", "descricao", "flagPrincipal");

	}

}
