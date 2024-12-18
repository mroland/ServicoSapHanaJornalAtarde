package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ContasReceberLinhaDAO {

	public void inserirComBrokerInterface(ContasReceberLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("contas_receber_linhas_id_seq"));
		
		broker.setSQL("INSERT INTO CONTAS_RECEBER_LINHAS(ID, PARCELA_ID, PARCELA_NUMERO, CONTAS_RECEBER_ID, EMPRESA_ID, VALOR_APLICADO) VALUES (?,?,?,?,?,?)", model.getInterfaceId(), model.getParcela().getId(), model.getParcela().getNumero(),
				 													  model.getContasReceber().getInterfaceId(), model.getEmpresa().getId(), model.getValorAplicado());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<ContasReceberLinha> pesquisarInterface(ContasReceber model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, PARCELA_ID, PARCELA_NUMERO, CONTAS_RECEBER_ID, EMPRESA_ID, VALOR_APLICADO FROM CONTAS_RECEBER_LINHAS WHERE CONTAS_RECEBER_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(ContasReceberLinha.class, "id", "parcela.id", "parcela.numero", "contasReceber.interfaceId", "empresa.id", "valorAplicado");
	}

}
