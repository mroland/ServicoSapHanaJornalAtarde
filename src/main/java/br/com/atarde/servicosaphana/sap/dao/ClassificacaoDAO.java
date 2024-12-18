/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Classificacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ClassificacaoDAO {

	public ClassificacaoDAO() {
	}

	public Classificacao obter(Classificacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCRG.\"GroupCode\" GROUPCODE FROM " + model.getEmpresa().getDbInstancia() + ".OCRG WHERE OCRG.\"GroupCode\" = ? AND OCRG.\"GroupType\"  = ?", model.getId(), model.getParceiroNegocio().getTipo());

		return (Classificacao) broker.getObjectBean(Classificacao.class, "id");

	}

}
