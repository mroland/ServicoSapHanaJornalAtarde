package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaidaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoClassificadosContratoNotaFiscalSaidaParcelaDAO {

	public ClassificadosContratoNotaFiscalSaidaParcela inserirInterface(ClassificadosContratoNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_contrato_nff_saida_parcelas_id_seq"));
		
        broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA_PARCELAS(ID, HISTORICO_NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
