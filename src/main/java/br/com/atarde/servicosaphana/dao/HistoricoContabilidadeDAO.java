package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.HistoricoContabilidade;
import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContabilidadeDAO {

	public void inserirInterface(HistoricoContabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_contabilidades_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_CONTABILIDADES (ID, DATA_VENCIMENTO, OBSERVACAO, DATA_LANCAMENTO, DATA_DOCUMENTO, EMPRESA_ID, STATUS_ID, DATA_IMPORTACAO, DATA_EXPORTACAO, MENSAGEM_ERRO, DATA_ATUALIZACAO,SAP_ID, REFERENCIA2) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getDataVencimento(), model.getObservacao(), model.getDataLancamento(), model.getDataDocumento(),
				                  model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao(), model.getId(), model.getReferencia2());
		
		broker.execute();
		
		for (ContabilidadeLinha item : model.getLinhas()) {
			
			item.setContabilidade(new Contabilidade());
			
			item.getContabilidade().setInterfaceId(model.getInterfaceId());
			
			new HistoricoContabilidadeLinhaDAO().inserir(item, broker);
			
		}
		
		broker.endTransaction();
		
	}

}
