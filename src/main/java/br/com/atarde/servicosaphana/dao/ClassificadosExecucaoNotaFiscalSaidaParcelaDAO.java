package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaidaParcela;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ClassificadosExecucaoNotaFiscalSaidaParcelaDAO {

	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(ClassificadosExecucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR FROM CLASSIFICADOS_EXECUCAO_NFF_SAIDA_PARCELAS WHERE NOTAFISCALSAIDA_ID = ? ORDER BY DATA_VENCIMENTO ", model.getInterfaceId());

		return broker.getCollectionBean(ClassificadosExecucaoNotaFiscalSaidaParcela.class, "interfaceId", "notaFiscalSaida.interfaceId", "dataVencimento", "valor");

	}

	public ClassificadosExecucaoNotaFiscalSaidaParcela inserirInterface(ClassificadosExecucaoNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("classificados_execucao_nff_saida_parcelas_id_seq"));

		broker.setSQL("INSERT INTO CLASSIFICADOS_EXECUCAO_NFF_SAIDA_PARCELAS(ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), model.getDataVencimento(), model.getValor());

		broker.execute();

		return model;
		
	}

}
