/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Municipio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class MunicipioDAO {

	public Municipio obter(Municipio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCNT.\"AbsId\" FROM " + model.getEmpresa().getDbInstancia() + ".OCNT WHERE OCNT.\"Country\" = ? AND OCNT.\"State\" = ? AND UPPER(OCNT.\"Name\") = UPPER(?)", model.getEnderecoAB().getPais().getId(), model.getEnderecoAB().getEstado().getId(), model.getEnderecoAB().getCidade());

		return (Municipio) broker.getObjectBean(Municipio.class, "id");
	}

}
