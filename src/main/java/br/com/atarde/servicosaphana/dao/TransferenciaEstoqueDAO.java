package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueDAO {

	public TransferenciaEstoque obter(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TRANSFERENCIA_ESTOQUE_ID FROM TRANSFERENCIA_ESTOQUE TE WHERE TE.NOTA_FISCAL_SAIDA_REFERENCIA_ID = ?", model.getNotaFiscalSaidaReferenciada().getId());

		return (TransferenciaEstoque) broker.getObjectBean(TransferenciaEstoque.class, "interfaceId", "idExterno", "dataDocumento", "dataLancamento", "dataVencimento", "observacaoDiario", "observacao", "estoqueOrigem.id", "estoqueDestino.id", "empresa.id", "origem.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId", "id");

	}

	@SuppressWarnings("unchecked")
	public List<TransferenciaEstoqueLinha> pesquisarLinhas(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, TRANSFERENCIA_ESTOQUE_ID, ITEM_ID, QUANTIDADE, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID FROM TRANSFERENCIA_ESTOQUE_LINHA TE WHERE TE.TRANSFERENCIA_ESTOQUE_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(TransferenciaEstoqueLinha.class, "interfaceId", "transferenciaEstoque.id", "item.id", "quantidade", "estoqueOrigem.id", "estoqueDestino.id");

	}

	public void excluirInterfacePorNota(NotaFiscalSaida model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM TRANSFERENCIA_ESTOQUE WHERE NOTA_FISCAL_SAIDA_REFERENCIA_ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<TransferenciaEstoque> pesquisarInterface(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, MENSAGEM_ERRO, STATUS_ID, NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TRANSFERENCIA_ESTOQUE_ID FROM TRANSFERENCIA_ESTOQUE TE WHERE EMPRESA_ID = ? AND STATUS_ID NOT IN(1,2) ORDER BY ID LIMIT 100 ", model.getEmpresa().getId());

		return broker.getCollectionBean(TransferenciaEstoque.class, "interfaceId", "idExterno", "dataDocumento", "dataLancamento", "dataVencimento", "observacaoDiario", "observacao", "estoqueOrigem.id", "estoqueDestino.id", "empresa.id", "origem.id", "filial.id", "dataExportacao", "dataImportacao", "dataAtualizacao", "mensagemErro", "status.id", "notaFiscalSaidaReferenciada.interfaceId", "id");

	}

	public void alterarInterface(TransferenciaEstoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TRANSFERENCIA_ESTOQUE SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ?, SAP_TRANSFERENCIA_ESTOQUE_ID = COALESCE(?,SAP_TRANSFERENCIA_ESTOQUE_ID) WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), TSUtil.isEmpty(model.getId()) ? null : model.getId(), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(TransferenciaEstoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TRANSFERENCIA_ESTOQUE WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<TransferenciaEstoque> pesquisarPorAtrasadaInterface(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM TRANSFERENCIA_ESTOQUE A WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(TransferenciaEstoque.class, "interfaceId");

	}

}
