package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoAssinaturaNotaFiscalSaidaParcelaDAO {

	public AssinaturaNotaFiscalSaidaParcela inserirInterface(AssinaturaNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturanotafiscalsaida_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_ASSINATURANOTAFISCALSAIDA_PARCELAS(ID, HISTORICO_NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
