package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaParcela;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EasyclassPedidoVendaParcelaDAO {

	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(EasyclassPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, PEDIDOVENDA_ID, DATA_VENCIMENTO, VALOR FROM EASYCLASS_PEDIDOVENDA_PARCELAS WHERE PEDIDOVENDA_ID = ? ORDER BY DATA_VENCIMENTO ", model.getInterfaceId());

		return broker.getCollectionBean(EasyclassPedidoVendaParcela.class, "interfaceId", "pedidoVenda.interfaceId", "dataVencimento", "valor");

	}

}
