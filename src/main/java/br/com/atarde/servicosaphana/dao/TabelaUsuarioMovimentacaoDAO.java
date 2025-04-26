package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoDAO {

	@SuppressWarnings("unchecked")
	public List<TabelaUsuarioMovimentacao> pesquisarInterface(TabelaUsuarioMovimentacao model) {

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

		if (!TSUtil.isEmpty(model.getEmpresa()) && !TSUtil.isEmpty(model.getEmpresa().getId())) {

			sql.append(" AND M.EMPRESA_ID = ? ");

			params.add(model.getEmpresa().getId());

		}

		broker.setSQL(sql.toString(), params.toArray());

		return broker.getCollectionBean(TabelaUsuarioMovimentacao.class, "interfaceId", "item.id", "quantidade", "tipoMovimentacao", "idExterno", "empresa.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId", "id", "status.id", "arquivoRemessa", "sapNotaFiscalSaidaId", "sapDevolucaoNotaFiscalSaidaId");

	}

	public void atualizarNotaFiscal(TabelaUsuarioMovimentacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TABELA_USUARIO_MOVIMENTACAO SET SAP_NOTA_FISCAL_SAIDA_ID = ? WHERE ID = ?", model.getSapNotaFiscalSaidaId(), model.getInterfaceId());

		broker.execute();

	}

	public void alterarInterface(TabelaUsuarioMovimentacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TABELA_USUARIO_MOVIMENTACAO SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(TabelaUsuarioMovimentacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TABELA_USUARIO_MOVIMENTACAO WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<TabelaUsuarioMovimentacao> pesquisarPorAtrasadaInterface(TabelaUsuarioMovimentacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM TABELA_USUARIO_MOVIMENTACAO A WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(TabelaUsuarioMovimentacao.class, "interfaceId");
	}

}
