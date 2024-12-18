package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContasReceberLinhaDAO {

	public void inserirInterface(ContasReceberLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_contas_receber_linhas_id_seq"));
		
		broker.setSQL("INSERT INTO HISTORICO_CONTAS_RECEBER_LINHAS(ID, PARCELA_ID, PARCELA_NUMERO, HISTORICO_CONTAS_RECEBER_ID, EMPRESA_ID, VALOR_APLICADO) VALUES (?,?,?,?,?,?)", model.getInterfaceId(), model.getParcela().getId(), model.getParcela().getNumero(),
				 													  model.getContasReceber().getInterfaceId(), model.getEmpresa().getId(), model.getValorAplicado());
		
		broker.execute();
		
	}

}
