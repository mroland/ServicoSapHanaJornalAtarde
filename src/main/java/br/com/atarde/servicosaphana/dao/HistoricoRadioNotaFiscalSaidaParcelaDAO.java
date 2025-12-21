package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoRadioNotaFiscalSaidaParcelaDAO {

	public RadioNotaFiscalSaidaParcela inserirInterface(RadioNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_radio_nff_saida_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_RADIO_NFF_SAIDA_PARCELAS(ID, HISTORICO_NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
