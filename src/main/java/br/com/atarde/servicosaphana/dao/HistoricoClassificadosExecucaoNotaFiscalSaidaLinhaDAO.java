package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoClassificadosExecucaoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(ClassificadosExecucaoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_execucao_nff_saida_linha_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_EXECUCAO_NFF_SAIDA_LINHA (ID , ITEM , QUANTIDADE , VALOR , CODIGO_IMPOSTO , HISTORICO_CLASSIFICADOS_EXECUCAO_NFF_SAIDA_ID , UTILIZACAO_ID , DEPOSITO_ID) VALUES (?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValor(), model.getCodigoImposto().getId(), model.getNotaFiscalSaida().getInterfaceId(), model.getUtilizacao().getId(), model.getEstoque().getId());

		broker.execute();

	}

}
