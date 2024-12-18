package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.Item;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ItemDAO {

	public Item obter(Item model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setSQL("SELECT OITM.\"ItemCode\" FROM "+ model.getEmpresa().getDbInstancia() + ".OITM WHERE OITM.\"ItemCode\" = ?", model.getId());

        return (Item) broker.getObjectBean(Item.class, "id");
        
	}

	@SuppressWarnings("unchecked")
	public List<Item> pesquisar(Item model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setSQL("SELECT ITEM.\"ItemCode\" AS ID_EXTERNO, ITEM.\"ItemName\" AS DESCRICAO, PRECO.\"Price\" AS VALOR_UNITARIO , LINHA.\"OnHand\" AS QTD_DISPONIVEL FROM " + model.getEmpresa().getDbInstancia() + ".OITM ITEM , " + model.getEmpresa().getDbInstancia() + ".ITM1 PRECO, " + model.getEmpresa().getDbInstancia() + ".OITW AS LINHA WHERE ITEM.\"ItemCode\" = PRECO.\"ItemCode\" AND LINHA.\"ItemCode\" = ITEM.\"ItemCode\" AND PRECO.\"PriceList\" = 3 AND LINHA.\"OnHand\" > 0 AND LINHA.\"WhsCode\" = ? ", model.getEstoque().getId());

        return broker.getCollectionBean(Item.class, "id", "descricao", "preco", "estoqueItem.quantidadeDisponivel");
	}

}
