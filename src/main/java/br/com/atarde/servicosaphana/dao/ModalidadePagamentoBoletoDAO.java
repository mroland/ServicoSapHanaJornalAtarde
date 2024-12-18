package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ModalidadePagamentoBoleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ModalidadePagamentoBoletoDAO {

    public ModalidadePagamentoBoleto obter(ContasReceber model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setSQL("SELECT ID, VALOR, FORMA_PAGAMENTO_ID, REFERENCIA, DATA_VENCIMENTO, CONTAS_RECEBER_ID FROM MODALIDADE_PAGAMENTO_BOLETO WHERE CONTAS_RECEBER_ID = ?", model.getInterfaceId());

        return (ModalidadePagamentoBoleto) broker.getObjectBean(ModalidadePagamentoBoleto.class, "interfaceId", "valor", "formaPagamento.id", "referencia", "dataVencimento", "contasReceber.interfaceId");

    }

	public void inserirComBrokerInterface(ModalidadePagamentoBoleto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("modalidade_pagamento_boleto_id_seq"));
		
		broker.setSQL("INSERT INTO MODALIDADE_PAGAMENTO_BOLETO(ID, CONTAS_RECEBER_ID, VALOR, FORMA_PAGAMENTO_ID, REFERENCIA, DATA_VENCIMENTO) VALUES(?,?,?,?,?,?)", model.getInterfaceId(), model.getContasReceber().getInterfaceId(),
																			    model.getValor(), model.getFormaPagamento().getId(), model.getReferencia(),
																			    model.getDataVencimento());
		
		broker.execute();
		
	}
}
