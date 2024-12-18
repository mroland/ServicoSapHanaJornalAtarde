package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ModalidadePagamentoTransferencia;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoModalidadePagamentoTransferenciaDAO {

	public void inserirInterface(ModalidadePagamentoTransferencia model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_modalidade_pagamento_transferencia_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_MODALIDADE_PAGAMENTO_TRANSFERENCIA(ID, HISTORICO_CONTAS_RECEBER_ID, VALOR, CONTA_CONTABIL_ID, REFERENCIA, DATA_TRANSFERENCIA) VALUES(?,?,?,?,?,?)", model.getInterfaceId(), model.getContasReceber().getInterfaceId(), model.getValor(),
																					   model.getContaContabil().getId(), model.getReferencia(), model.getDataTransferencia());
		
		broker.execute();
		
	}

}
