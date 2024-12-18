package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ModalidadePagamentoBoleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoModalidadePagamentoBoletoDAO {

	public void inserirInterface(ModalidadePagamentoBoleto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_modalidade_pagamento_boleto_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_MODALIDADE_PAGAMENTO_BOLETO(ID, HISTORICO_CONTAS_RECEBER_ID, VALOR, FORMA_PAGAMENTO_ID, REFERENCIA, DATA_VENCIMENTO) VALUES(?,?,?,?,?,?)", model.getInterfaceId(), model.getContasReceber().getInterfaceId(),
																			    model.getValor(), model.getFormaPagamento().getId(), model.getReferencia(),
																			    model.getDataVencimento());
		
		broker.execute();
		
	}
}
