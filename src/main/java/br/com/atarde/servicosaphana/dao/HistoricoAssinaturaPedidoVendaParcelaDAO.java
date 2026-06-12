package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoAssinaturaPedidoVendaParcelaDAO {

	public AssinaturaPedidoVendaParcela inserirInterface(AssinaturaPedidoVendaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturapedidovenda_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_ASSINATURAPEDIDOVENDA_PARCELAS(ID, HISTORICO_PEDIDOVENDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getPedidoVenda().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
