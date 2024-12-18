package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Categoria;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CategoriaDAO {

	public Categoria obterPeloCodigo(Categoria model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setSQL("SELECT ONFT.\"AbsId\" ABSID FROM " + model.getEmpresa().getDbInstancia() +".ONFT WHERE ONFT.\"Code\"= ?", model.getCodigo());

        return (Categoria) broker.getObjectBean(Categoria.class, "id");
	}

}
