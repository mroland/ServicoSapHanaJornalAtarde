/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioEnderecoDAO {

	public ParceiroNegocioEnderecoDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<ParceiroNegocioEndereco> pesquisar(ParceiroNegocio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT CRD1.\"Address\" ADDRESS FROM " + model.getEmpresa().getDbInstancia() + ".CRD1 WHERE CRD1.\"CardCode\" = ?", model.getId());

		return broker.getCollectionBean(ParceiroNegocioEndereco.class, "id");

	}

	public ParceiroNegocioEndereco obter(ParceiroNegocioEndereco model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT CRD1.\"Address\" FROM " + model.getEmpresa().getDbInstancia() + ".CRD1 WHERE CRD1.\"CardCode\" =? AND CRD1.\"AdresType\" =? AND CRD1.\"Address\" = ?", model.getParceiroNegocio().getId(), model.getTipoEndereco(), model.getId());

		return (ParceiroNegocioEndereco) broker.getObjectBean(ParceiroNegocioEndereco.class, "id");
	}

}
