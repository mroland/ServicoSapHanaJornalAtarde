package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaParcela;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class RadioNotaFiscalSaidaParcelaDAO {

	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(RadioNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR FROM RADIO_NFF_SAIDA_PARCELAS WHERE NOTAFISCALSAIDA_ID = ? ORDER BY DATA_VENCIMENTO ", model.getInterfaceId());

		return broker.getCollectionBean(RadioNotaFiscalSaidaParcela.class, "interfaceId", "notaFiscalSaida.interfaceId", "dataVencimento", "valor");

	}

}
