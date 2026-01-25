package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinhaImposto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoRadioNotaFiscalSaidaLinhaImpostoDAO {

	public void inserirInterface(RadioNotaFiscalSaidaLinhaImposto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_radio_nff_saida_linha_imposto_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_RADIO_NFF_SAIDA_LINHA_IMPOSTO (ID, HISTORICO_RADIO_NFF_SAIDA_LINHA_ID, IMPOSTO_ID, VALOR, PERCENTUAL) VALUES (?,?,?,?,?)", model.getInterfaceId(), model.getLinha().getInterfaceId(), model.getImpostoId(), model.getValor(), model.getPercentual());

		broker.execute();

	}

}
