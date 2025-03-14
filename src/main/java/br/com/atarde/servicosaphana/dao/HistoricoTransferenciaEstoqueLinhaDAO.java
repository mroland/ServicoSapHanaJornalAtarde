package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoTransferenciaEstoqueLinhaDAO {

	public void inserirInterface(TransferenciaEstoqueLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_transferencia_estoque_linha_id_seq"));

		broker.setSQL("INSERT INTO PUBLIC.HISTORICO_TRANSFERENCIA_ESTOQUE_LINHA(ID, HISTORICO_TRANSFERENCIA_ESTOQUE_ID, ITEM_ID, QUANTIDADE) VALUES(?,?,?,?);", model.getInterfaceId(), model.getTransferenciaEstoque().getInterfaceId(), model.getItem().getId(), model.getQuantidade());

		broker.execute();

	}

}
