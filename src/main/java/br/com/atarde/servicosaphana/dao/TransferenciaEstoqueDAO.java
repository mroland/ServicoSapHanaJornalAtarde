package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class TransferenciaEstoqueDAO {

	public TransferenciaEstoque obter(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID FROM TRANSFERENCIA_ESTOQUE TE WHERE TE.NOTA_FISCAL_SAIDA_REFERENCIA_ID = ?", model.getNotaFiscalSaidaReferenciada().getId());

		return (TransferenciaEstoque) broker.getObjectBean(TransferenciaEstoque.class, "interfaceId", "idExterno", "dataDocumento", "dataLancamento", "dataVencimento", "observacaoDiario", "observacao", "estoqueOrigem.id", "estoqueDestino.id", "empresa.id", "origem.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId");

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

}
