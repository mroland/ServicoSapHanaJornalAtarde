package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoEasyclassNotaFiscalSaidaParcelaDAO {

	public EasyclassNotaFiscalSaidaParcela inserirInterface(EasyclassNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_easyclass_nff_saida_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_EASYCLASS_NFF_SAIDA_PARCELAS(ID, HISTORICO_NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
