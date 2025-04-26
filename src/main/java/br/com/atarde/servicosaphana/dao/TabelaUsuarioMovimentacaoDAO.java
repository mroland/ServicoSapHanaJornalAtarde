package br.com.atarde.servicosaphana.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoDAO {

	@SuppressWarnings("unchecked")
	public List<TabelaUsuarioMovimentacao> pesquisar(TabelaUsuarioMovimentacao model) {

		StringBuilder sql = new StringBuilder();

		List<Object> params = new ArrayList<Object>();

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		sql.append("SELECT ID, ITEM_ID, QUANTIDADE, TIPO_MOVIMENTACAO_ID, ID_EXTERNO, EMPRESA_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TABELA_USUARIO_ID, STATUS_ID, ARQUIVO_REMESSA, SAP_NOTA_FISCAL_SAIDA_ID, SAP_DEVOLUCAO_NOTA_FISCAL_SAIDA_ID FROM TABELA_USUARIO_MOVIMENTACAO M WHERE 1=1");

		if (!TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada().getInterfaceId())) {

			sql.append(" AND M.NOTA_FISCAL_SAIDA_REFERENCIA_ID = ? ");

			params.add(model.getNotaFiscalSaidaReferenciada().getInterfaceId());

		}

		if (!TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId())) {

			sql.append(" AND M.DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID = ? ");

			params.add(model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId());

		}

		broker.setSQL(sql.toString(), params.toArray());

		return broker.getCollectionBean(TabelaUsuarioMovimentacao.class, "interfaceId", "item.id", "quantidade", "tipoMovimentacao", "idExterno", "empresa.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId", "id", "status.id", "arquivoRemessa", "sapNotaFiscalId", "sapDevolucaoNotaFiscalId");

	}

	public void atualizarNotaFiscal(TabelaUsuarioMovimentacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TABELA_USUARIO_MOVIMENTACAO SET SAP_NOTA_FISCAL_SAIDA_ID = ? WHERE ID = ?", model.getSapNotaFiscalId(), model.getInterfaceId());

		broker.execute();

	}

}
