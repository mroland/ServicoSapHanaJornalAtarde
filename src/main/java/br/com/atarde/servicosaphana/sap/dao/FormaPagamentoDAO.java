/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.FormaPagamento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class FormaPagamentoDAO {

	@SuppressWarnings("unchecked")
	public List<FormaPagamento> pesquisar(FormaPagamento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT PG.\"PayMethCod\" FROM " + model.getEmpresa().getDbInstancia() + ".OPYM PG WHERE PG.\"Type\" = ?", model.getTipo());

		return broker.getCollectionBean(FormaPagamento.class, "id");

	}

	public FormaPagamento obter(FormaPagamento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setPropertySQL("SELECT OPYM.PayMethCod ID, OPYM.Descript DESCRICAO FROM" + model.getEmpresa().getDbInstancia() + ".OPYM WHERE OPYM.PayMethCod = ? AND OPYM.[Type] = ?", model.getId(), model.getTipo());

		return (FormaPagamento) broker.getObjectBean(FormaPagamento.class, "id", "descricao");
	}

}
