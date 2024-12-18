package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.HistoricoExtratoBancario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoExtratoBancarioDAO {

	public void inserirInterface(HistoricoExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_extrato_bancarios_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_EXTRATO_BANCARIOS(ID, CONTA, DATA_VENCIMENTO, NUMERO_DOCUMENTO, DETALHE, VALOR_DEBITO, SAP_CONTA_CONTABIL_ID, SAP_CODIGO_INTERNO_ID, VALOR_CREDITO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getConta(), model.getDataVencimento(), model.getNumeroDocumento(), model.getDetalhe(), model.getValorDebito(), model.getContaContabil().getId(), model.getCodigoInterno().getId(), model.getValorCredito(), model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao());
		
		broker.execute();
		
		broker.endTransaction();
		
	}

}
