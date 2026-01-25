package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaidaLinhaImposto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoClassificadosExecucaoNotaFiscalSaidaLinhaImpostoDAO {

	public void inserirInterface(ClassificadosExecucaoNotaFiscalSaidaLinhaImposto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_execucao_nff_saida_linha_imposto_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_EXECUCAO_NFF_SAIDA_LINHA_IMPOSTO (ID, HISTORICO_CLASSIFICADOS_EXECUCAO_NFF_SAIDA_LINHA_ID, IMPOSTO_ID, VALOR, PERCENTUAL) VALUES (?,?,?,?,?)", model.getInterfaceId(), model.getLinha().getInterfaceId(), model.getImpostoId(), model.getValor(), model.getPercentual());

		broker.execute();

	}

}
