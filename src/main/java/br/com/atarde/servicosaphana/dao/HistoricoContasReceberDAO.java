package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.HistoricoContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class HistoricoContasReceberDAO {

	public HistoricoContasReceber inserirInterface(HistoricoContasReceber model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_contas_receber_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_CONTAS_RECEBER(ID, PARCEIRO_NEGOCIO_ID,DATA_LANCAMENTO, DATA_DOCUMENTO, VALOR, STATUS_ID, TIPO_MODALIDADE, MENSAGEM_ERRO, DATA_EXPORTACAO, DATA_VENCIMENTO, EMPRESA_ID, DATA_IMPORTACAO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getDataLancamento(), 
                model.getDataDocumento(), model.getValor(), model.getStatus().getId(), 
                model.getTipoModalidade(), model.getMensagemErro(), new Timestamp(System.currentTimeMillis()),
                model.getDataVencimento(), model.getEmpresa().getId(), model.getDataImportacao());
		

		broker.execute();
		
		for (ContasReceberLinha linha : model.getLinhas()) {
			
			linha.setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			linha.setEmpresa(model.getEmpresa());

			new HistoricoContasReceberLinhaDAO().inserirInterface(linha,broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())){
			
			model.getModalidadePagamentoBoleto().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new HistoricoModalidadePagamentoBoletoDAO().inserirInterface(model.getModalidadePagamentoBoleto(), broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia())){
			
			model.getModalidadePagamentoTransferencia().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new HistoricoModalidadePagamentoTransferenciaDAO().inserirInterface(model.getModalidadePagamentoTransferencia(),broker);
			
		}
		
		broker.endTransaction();

		return model;
		
	}

}
