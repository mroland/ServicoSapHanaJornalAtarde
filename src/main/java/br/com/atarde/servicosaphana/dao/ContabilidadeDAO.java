package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ContabilidadeDAO {

	@SuppressWarnings("unchecked")
	public List<Contabilidade> pesquisarInterface(Contabilidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DATA_VENCIMENTO, OBSERVACAO, DATA_LANCAMENTO, DATA_DOCUMENTO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO, SAP_ID, REFERENCIA2 FROM CONTABILIDADES WHERE EMPRESA_ID = ? AND STATUS_ID !=2 LIMIT 200", model.getEmpresa().getId());
		
		return broker.getCollectionBean(Contabilidade.class, "interfaceId", "dataVencimento", "observacao", "dataLancamento" , "dataDocumento", 
				                                           "empresa.id", "status.id", "dataImportacao", "dataExportacao", "mensagemErro",
				                                           "dataAtualizacao", "id", "referencia2");

	}

	public void alterarInterface(Contabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE CONTABILIDADES SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO = ?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());
				
		broker.execute();
		
	}

	public void excluirInterface(Contabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("DELETE FROM CONTABILIDADES WHERE ID = ?", model.getInterfaceId());
				
		broker.execute();

		
	}

	@SuppressWarnings("unchecked")
	public List<Contabilidade> pesquisarPorAtrasadaInterface(Contabilidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DATA_ATUALIZACAO, DATA_IMPORTACAO FROM CONTABILIDADES WHERE STATUS_ID = 2 AND (DATE_PART('DAY', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP) * 24 + DATE_PART('HOUR', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP)) * 60 + DATE_PART('MINUTE', NOW()::TIMESTAMP - COALESCE(DATA_ATUALIZACAO,NOW())::TIMESTAMP) >=30");
		
		return broker.getCollectionBean(Contabilidade.class, "interfaceId", "dataAtualizacao", "dataImportacao");
		
	}

	public Contabilidade inserirInterface(Contabilidade model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("contabilidades_id_seq"));
		
		broker.setSQL("INSERT INTO CONTABILIDADES(ID, DATA_VENCIMENTO, OBSERVACAO, DATA_LANCAMENTO, DATA_DOCUMENTO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO, SAP_ID, REFERENCIA2) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getDataVencimento(), model.getObservacao(), model.getDataLancamento(),model.getDataDocumento(),
				                                          model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), 
				                                          model.getMensagemErro(), model.getDataAtualizacao(), model.getId(),model.getReferencia2());
		
		broker.execute();
		
		for (ContabilidadeLinha linha : model.getLinhas()) {
			
			linha.setContabilidade(new Contabilidade("interfaceId", model.getInterfaceId()));
			
			new ContabilidadeLinhaDAO().inserirInterface(linha,broker);
			
		}
		
		broker.endTransaction();

		return model;
	}

}
