package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoEasyclassPedidoVendaParcelaDAO {

	public EasyclassPedidoVendaParcela inserirInterface(EasyclassPedidoVendaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_easyclass_pedidovenda_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_EASYCLASS_PEDIDOVENDA_PARCELAS(ID, HISTORICO_PEDIDOVENDA_ID, DATA_VENCIMENTO, VALOR, VALOR_SEM_IMPOSTO_RETIDO) VALUES(?,?,?,?,?)",
        		model.getInterfaceId(),
        		model.getPedidoVenda().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor(),
                model.getValorSemImpostoRetido());

        broker.execute();
        
        return model;
	}
	
}
