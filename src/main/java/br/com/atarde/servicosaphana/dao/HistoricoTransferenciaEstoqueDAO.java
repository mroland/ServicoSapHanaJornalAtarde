package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.HistoricoTransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class HistoricoTransferenciaEstoqueDAO {

	public void inserirInterface(TransferenciaEstoque model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		Long interfaceOriginalId = model.getInterfaceId();

		model.setInterfaceId(broker.getSequenceNextValue("historico_transferencia_estoque_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_TRANSFERENCIA_ESTOQUE(ID, INTERFACE_ORIGINAL_ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, SAP_TRANSFERENCIA_ESTOQUE_ID, DATA_IMPORTACAO, DATA_ATUALIZACAO, STATUS_ID, MENSAGEM_ERRO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", model.getInterfaceId(), interfaceOriginalId, model.getIdExterno(), model.getDataDocumento(), model.getDataLancamento(), model.getDataVencimento(), model.getObservacaoDiario(), model.getObservacao(), model.getEstoqueOrigem().getId(), model.getEstoqueDestino().getId(), model.getEmpresa().getId(), model.getOrigem().getId(), model.getFilial().getId(), new Timestamp(model.getDataExportacao().getTime()), model.getNotaFiscalSaidaReferenciada().getInterfaceId(), model.getId(), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), model.getStatus().getId(), model.getMensagemErro());

		broker.execute();

		for (TransferenciaEstoqueLinha linha : model.getLinhas()) {

			linha.setTransferenciaEstoque(new TransferenciaEstoque(model.getInterfaceId()));

			new HistoricoTransferenciaEstoqueLinhaDAO().inserirInterface(linha, broker);

		}
	}

	public void inserirInterface(HistoricoTransferenciaEstoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		this.inserirInterface(model, broker);

		broker.endTransaction();

	}

}
