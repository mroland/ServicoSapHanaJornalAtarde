package br.com.atarde.servicosaphana.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.model.VendaVulsaNotaFiscalSaidaMovimentacao;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;

public class VendaVulsaNotaFiscalSaidaMovimentacaoDAO {

	@SuppressWarnings("unchecked")
	public List<VendaVulsaNotaFiscalSaidaMovimentacao> pesquisar(VendaVulsaNotaFiscalSaidaMovimentacao model) {

		StringBuilder sql = new StringBuilder();

		List<Object> params = new ArrayList<Object>();

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		sql.append("SELECT ID, ITEM_ID, QUANTIDADE, TIPO_MOVIMENTACAO_ID, ID_EXTERNO, EMPRESA_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_CODE_ID, STATUS_ID, ARQUIVO_REMESSA, SAP_NOTA_FISCAL_ID FROM VENDAAVULSANOTAFISCALSAIDA_MOVIMENTACAO M WHERE 1=1");

		if (!TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) && !TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada().getInterfaceId())) {

			sql.append(" AND M.NOTA_FISCAL_SAIDA_REFERENCIA_ID = ? ");

			params.add(model.getNotaFiscalSaidaReferenciada().getInterfaceId());

		}

		broker.setSQL(sql.toString(), params.toArray());

		return broker.getCollectionBean(VendaVulsaNotaFiscalSaidaMovimentacao.class, "interfaceId", "item.id", "quantidade", "tipoMovimentacao", "idExterno", "empresa.id", "filial.id", "dataExportacao", "notaFiscalSaidaReferenciada.interfaceId", "id", "status.id", "arquivoRemessa", "sapNotaFiscalId");

	}

	public void atualizar(NotaFiscalSaida notaFiscalSaida, Long sapNotaFiscalId) {

		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE VENDAAVULSANOTAFISCALSAIDA_MOVIMENTACAO SET ");
		
	}

}
