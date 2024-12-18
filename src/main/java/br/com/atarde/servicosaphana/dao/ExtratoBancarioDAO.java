package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ExtratoBancarioDAO {

	@SuppressWarnings("unchecked")
	public List<ExtratoBancario> pesquisarInterface(ExtratoBancario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, CONTA, DATA_VENCIMENTO, NUMERO_DOCUMENTO, DETALHE, VALOR_DEBITO, SAP_CONTA_CONTABIL_ID, SAP_CODIGO_INTERNO_ID, VALOR_CREDITO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO FROM EXTRATO_BANCARIOS WHERE EMPRESA_ID = ? AND STATUS_ID !=2 ORDER BY SAP_CONTA_CONTABIL_ID,DATA_VENCIMENTO, ID LIMIT 200", model.getEmpresa().getId());

		return broker.getCollectionBean(ExtratoBancario.class, "interfaceId", "conta", "dataVencimento", "numeroDocumento", "detalhe", "valorDebito", "contaContabil.id", "codigoInterno.id", "valorCredito", "empresa.id", "status.id", "dataImportacao", "dataExportacao", "mensagemErro", "dataAtualizacao");

	}

	public void alterarInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE EXTRATO_BANCARIOS SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO = ?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("DELETE FROM EXTRATO_BANCARIOS WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ExtratoBancario> pesquisarPorAtrasadaInterface(ExtratoBancario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DATA_ATUALIZACAO, DATA_IMPORTACAO FROM EXTRATO_BANCARIOS WHERE STATUS_ID = 2 AND (DATE_PART('DAY', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP) * 24 + DATE_PART('HOUR', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP)) * 60 + DATE_PART('MINUTE', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP) >=30");

		return broker.getCollectionBean(ExtratoBancario.class, "interfaceId", "dataAtualizacao", "dataImportacao");

	}

	public ExtratoBancario inserirInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("extrato_bancarios_id_seq"));

		broker.setSQL("INSERT INTO EXTRATO_BANCARIOS(ID, CONTA, DATA_VENCIMENTO, NUMERO_DOCUMENTO, DETALHE, VALOR_DEBITO, SAP_CONTA_CONTABIL_ID, SAP_CODIGO_INTERNO_ID, VALOR_CREDITO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getConta(), model.getDataVencimento(), model.getNumeroDocumento(), model.getDetalhe(), model.getValorDebito(), model.getContaContabil().getId(), model.getCodigoInterno().getId(), model.getValorCredito(), model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao());

		broker.execute();

		broker.endTransaction();

		return model;
	}

}
