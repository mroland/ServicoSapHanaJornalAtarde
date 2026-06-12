package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.AssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaParcela;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class AssinaturaPedidoVendaParcelaDAO {

	public AssinaturaPedidoVendaParcela inserirInterface(AssinaturaPedidoVendaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("assinaturapedidovenda_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO ASSINATURAPEDIDOVENDA_PARCELAS(ID, PEDIDOVENDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getPedidoVenda().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(AssinaturaPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, PEDIDOVENDA_ID, DATA_VENCIMENTO, VALOR FROM ASSINATURAPEDIDOVENDA_PARCELAS WHERE PEDIDOVENDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(AssinaturaPedidoVendaParcela.class, "interfaceId", "pedidoVenda.interfaceId", "dataVencimento", "valor");
	}	

}
