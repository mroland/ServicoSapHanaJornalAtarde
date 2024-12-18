package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.Estoque;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;

public class EstoqueDAO {

	@SuppressWarnings("unchecked")
	public List<Estoque> pesquisar(Estoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ESTOQUE.\"WhsCode\" AS ID, ESTOQUE.\"WhsName\" AS NOME FROM " + model.getEmpresa().getDbInstancia() + ".OWHS ESTOQUE WHERE EXISTS (SELECT 1 FROM " + model.getEmpresa().getDbInstancia() + ".OITM ITEM, " + model.getEmpresa().getDbInstancia() + ".OITW LINHA WHERE LINHA.\"ItemCode\" = ITEM.\"ItemCode\" AND ESTOQUE.\"WhsCode\" = LINHA.\"WhsCode\" AND LINHA.\"OnHand\" > 0 ");

		if (!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))) {

			sql.append(" AND ITEM.\"ItemCode\" = ? )");

		} else {

			sql.append(" )");
		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))) {

			broker.set(model.getItem().getId());

		}

		return broker.getCollectionBean(Estoque.class, "id", "descricao");

	}

	public Estoque obter(Estoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ESTOQUE.\"WhsCode\" AS ID, ESTOQUE.\"WhsName\" AS NOME FROM " + model.getEmpresa().getDbInstancia() + ".OWHS ESTOQUE WHERE EXISTS (SELECT 1 FROM " + model.getEmpresa().getDbInstancia() + ".OITM ITEM, " + model.getEmpresa().getDbInstancia() + ".OITW LINHA WHERE LINHA.\"ItemCode\" = ITEM.\"ItemCode\" AND ESTOQUE.\"WhsCode\" = LINHA.\"WhsCode\" AND LINHA.\"OnHand\" > 0 ");

		if (!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))) {

			sql.append(" AND ITEM.\"ItemCode\" = ? )");

		} else {

			sql.append(" )");
		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))) {

			broker.set(model.getItem().getId());

		}

		return (Estoque) broker.getObjectBean(Estoque.class, "id", "descricao");

	}

}
