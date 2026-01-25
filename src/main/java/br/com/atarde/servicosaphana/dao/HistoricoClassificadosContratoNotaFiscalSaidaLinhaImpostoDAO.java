package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaidaLinhaImposto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoClassificadosContratoNotaFiscalSaidaLinhaImpostoDAO {

	public void inserirInterface(ClassificadosContratoNotaFiscalSaidaLinhaImposto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_contrato_nff_saida_linha_imposto_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA_LINHA_IMPOSTO (ID, HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA_LINHA_ID, IMPOSTO_ID, VALOR, PERCENTUAL) VALUES (?,?,?,?,?)", model.getInterfaceId(), model.getLinha().getInterfaceId(), model.getImpostoId(), model.getValor(), model.getPercentual());

		broker.execute();

	}

}
