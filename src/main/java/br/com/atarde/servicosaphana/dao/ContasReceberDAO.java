package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ContasReceberDAO {

    @SuppressWarnings("unchecked")
    public List<ContasReceber> pesquisarInterface(ContasReceber model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setSQL("SELECT ID,PARCEIRO_NEGOCIO_ID,DATA_LANCAMENTO,DATA_DOCUMENTO,DATA_VENCIMENTO,VALOR,STATUS_ID,TIPO_MODALIDADE, MENSAGEM_ERRO, DATA_EXPORTACAO, EMPRESA_ID, DATA_IMPORTACAO FROM CONTAS_RECEBER WHERE EMPRESA_ID = ? AND STATUS_ID !=2 LIMIT 200", model.getEmpresa().getId());

        return broker.getCollectionBean(ContasReceber.class, "interfaceId", "cliente.id", "dataLancamento", "dataDocumento",
                "dataVencimento", "valor", "status.id", "tipoModalidade", "mensagemErro", "dataExportacao", "empresa.id", "dataImportacao");
    }

    public void alterarInterface(ContasReceber model) throws TSApplicationException {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setSQL("UPDATE CONTAS_RECEBER SET STATUS_ID = ?, MENSAGEM_ERRO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), model.getInterfaceId());

        broker.execute();

    }

    @SuppressWarnings("unchecked")
	public List<ContasReceber> pesquisarPorAtrasadaInterface(ContasReceber model) {
        
        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
        
        broker.setSQL("SELECT ID FROM CONTAS_RECEBER WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());
        
        return broker.getCollectionBean(ContasReceber.class, "interfaceId");
    }

	public ContasReceber inserirInterface(ContasReceber model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("contas_receber_id_seq"));
		
		broker.setSQL("INSERT INTO CONTAS_RECEBER(ID, PARCEIRO_NEGOCIO_ID,DATA_LANCAMENTO, DATA_DOCUMENTO, VALOR, STATUS_ID, TIPO_MODALIDADE, MENSAGEM_ERRO, DATA_EXPORTACAO, DATA_VENCIMENTO, EMPRESA_ID, DATA_IMPORTACAO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getDataLancamento(), 
				                                           model.getDataDocumento(), model.getValor(), model.getStatus().getId(), 
				                                           model.getTipoModalidade(), model.getMensagemErro(), new Timestamp(System.currentTimeMillis()),
				                                           model.getDataVencimento(), model.getEmpresa().getId(), model.getDataImportacao());
		
		broker.execute();
		
		for (ContasReceberLinha linha : model.getLinhas()) {
			
			linha.setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			linha.setEmpresa(model.getEmpresa());

			new ContasReceberLinhaDAO().inserirComBrokerInterface(linha,broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())){
			
			model.getModalidadePagamentoBoleto().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new ModalidadePagamentoBoletoDAO().inserirComBrokerInterface(model.getModalidadePagamentoBoleto(), broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia())){
			
			model.getModalidadePagamentoTransferencia().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new ModalidadePagamentoTransferenciaDAO().inserirComBrokerInterface(model.getModalidadePagamentoTransferencia(),broker);
			
		}
		

		broker.endTransaction();

		return model;
		
	}

	public void excluirInterface(ContasReceber model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("DELETE FROM CONTAS_RECEBER WHERE ID = ?", model.getInterfaceId());
		
		broker.execute();
		
	}

}
