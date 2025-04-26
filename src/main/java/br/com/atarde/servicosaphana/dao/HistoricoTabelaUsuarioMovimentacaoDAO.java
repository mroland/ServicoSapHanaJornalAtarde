package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.HistoricoTabelaUsuarioMovimentacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class HistoricoTabelaUsuarioMovimentacaoDAO {

	public void inserirInterface(HistoricoTabelaUsuarioMovimentacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		Long interfaceOriginalId = model.getInterfaceId();

		model.setInterfaceId(broker.getSequenceNextValue("historico_tabela_usuario_movimentacao_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_TABELA_USUARIO_MOVIMENTACAO(ID, INTERFACE_ORIGINAL_ID, TIPO_MOVIMENTACAO_ID, ID_EXTERNO, ITEM_ID, QUANTIDADE, EMPRESA_ID, FILIAL_ID, DATA_IMPORTACAO, DATA_ATUALIZACAO, DATA_EXPORTACAO, STATUS_ID, MENSAGEM_ERRO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TABELA_USUARIO_ID, ARQUIVO_REMESSA, SAP_NOTA_FISCAL_SAIDA_ID, SAP_DEVOLUCAO_NOTA_FISCAL_SAIDA_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", model.getInterfaceId(), interfaceOriginalId, model.getTipoMovimentacao(), model.getIdExterno(), model.getItem().getId(), model.getQuantidade(), model.getEmpresa().getId(), model.getFilial().getId(), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), new Timestamp(model.getDataExportacao().getTime()), model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) ? null : model.getNotaFiscalSaidaReferenciada().getInterfaceId(), TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) ? null : model.getDevolucaoNotaFiscalSaidaReferenciada().getInterfaceId(), model.getId(), model.getArquivoRemessa(), model.getSapNotaFiscalSaidaId(), model.getSapDevolucaoNotaFiscalSaidaId());

		broker.execute();
	}

}
