package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaParcela;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EasyclassNotaFiscalSaidaParcelaDAO {

	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(EasyclassNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR FROM EASYCLASS_NFF_SAIDA_PARCELAS WHERE NOTAFISCALSAIDA_ID = ? ORDER BY DATA_VENCIMENTO ", model.getInterfaceId());

		return broker.getCollectionBean(EasyclassNotaFiscalSaidaParcela.class, "interfaceId", "notaFiscalSaida.interfaceId", "dataVencimento", "valor");

	}

}
