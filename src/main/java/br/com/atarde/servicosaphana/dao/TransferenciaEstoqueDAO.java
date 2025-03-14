package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueDAO {

	public TransferenciaEstoque obter(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		List<Object> params = new ArrayList<Object>();

		sql.append("SELECT ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TRANSFERENCIA_ESTOQUE_ID, STATUS_ID FROM TRANSFERENCIA_ESTOQUE TE WHERE 1 = 1 ");

		if (!TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada().getInterfaceId())) {

			sql.append(" AND TE.NOTA_FISCAL_SAIDA_REFERENCIA_ID = ? ");

			params.add(model.getNotaFiscalSaidaReferenciada().getInterfaceId());

		}

		if (!TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId())) {

			sql.append(" AND TE.DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID = ? ");

			params.add(model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId());

		}

		if (!TSUtil.isEmpty(model.getOrigem()) && !TSUtil.isEmpty(model.getOrigem().getId())) {

			sql.append(" AND ORIGEM_ID = ? ");

			params.add(model.getOrigem().getId());

		}

		broker.setSQL(sql.toString(), params.toArray());

		return (TransferenciaEstoque) broker.getObjectBean(TransferenciaEstoque.class, "interfaceId", "idExterno", "dataDocumento", "dataLancamento", "dataVencimento", "observacaoDiario", "observacao", "estoqueOrigem.id", "estoqueDestino.id", "empresa.id", "origem.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId", "devolucaoNotaFiscalSaidaReferenciada.interfaceId", "id", "status.id");

	}

	@SuppressWarnings("unchecked")
	public List<TransferenciaEstoqueLinha> pesquisarLinhas(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, TRANSFERENCIA_ESTOQUE_ID, ITEM_ID, QUANTIDADE FROM TRANSFERENCIA_ESTOQUE_LINHA TE WHERE TE.TRANSFERENCIA_ESTOQUE_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(TransferenciaEstoqueLinha.class, "interfaceId", "transferenciaEstoque.id", "item.id", "quantidade");

	}

	public void excluirInterfacePorNota(NotaFiscalSaida model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM TRANSFERENCIA_ESTOQUE WHERE NOTA_FISCAL_SAIDA_REFERENCIA_ID = ?", model.getInterfaceId());

		broker.execute();

	}
	
	public void excluirInterfacePorNota(DevolucaoNotaFiscalSaida model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM TRANSFERENCIA_ESTOQUE WHERE DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<TransferenciaEstoque> pesquisarInterface(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, MENSAGEM_ERRO, STATUS_ID, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TRANSFERENCIA_ESTOQUE_ID FROM TRANSFERENCIA_ESTOQUE TE WHERE EMPRESA_ID = ? AND STATUS_ID NOT IN(1,2) ORDER BY ID LIMIT 100 ", model.getEmpresa().getId());

		return broker.getCollectionBean(TransferenciaEstoque.class, "interfaceId", "idExterno", "dataDocumento", "dataLancamento", "dataVencimento", "observacaoDiario", "observacao", "estoqueOrigem.id", "estoqueDestino.id", "empresa.id", "origem.id", "filial.id", "dataExportacao", "dataImportacao", "dataAtualizacao", "mensagemErro", "status.id", "notaFiscalSaidaReferenciada.interfaceId", "devolucaoNotaFiscalSaidaReferenciada.interfaceId", "id");

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
