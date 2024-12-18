package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ModalidadePagamentoTransferencia;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ModalidadePagamentoTransferenciaDAO {

	public ModalidadePagamentoTransferencia obter(ContasReceber model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, VALOR, CONTA_CONTABIL_ID, REFERENCIA, DATA_TRANSFERENCIA, CONTAS_RECEBER_ID FROM MODALIDADE_PAGAMENTO_TRANSFERENCIA WHERE CONTAS_RECEBER_ID = ?", model.getInterfaceId());
		
		return (ModalidadePagamentoTransferencia) broker.getObjectBean(ModalidadePagamentoTransferencia.class, "interfaceId", "valor", "contaContabil.id", "referencia", "dataTransferencia", "contasReceber.interfaceId");
	}

	public void inserirComBrokerInterface(ModalidadePagamentoTransferencia model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("modalidade_pagamento_transferencia_id_seq"));
		
		broker.setSQL("INSERT INTO MODALIDADE_PAGAMENTO_TRANSFERENCIA(ID, CONTAS_RECEBER_ID, VALOR, CONTA_CONTABIL_ID, REFERENCIA, DATA_TRANSFERENCIA) VALUES(?,?,?,?,?,?)", model.getInterfaceId(), model.getContasReceber().getInterfaceId(), model.getValor(),
																					   model.getContaContabil().getId(), model.getReferencia(), model.getDataTransferencia());
		
		broker.execute();
		
	}

}
