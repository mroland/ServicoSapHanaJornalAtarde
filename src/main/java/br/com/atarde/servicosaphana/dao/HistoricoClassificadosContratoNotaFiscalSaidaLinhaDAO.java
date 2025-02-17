package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoClassificadosContratoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(ClassificadosContratoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_contrato_nff_saida_linha_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA_LINHA (ID , ITEM , QUANTIDADE , VALOR_UNITARIO , VALOR , CODIGO_IMPOSTO , HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA_ID , U_CMXCOL , U_AREA , U_QUANTIDADE_INSERCOES , U_TOTAL_CMXCOL , U_VALOR_UNITARIO , UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getNotaFiscalSaida().getInterfaceId(), model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(), model.getUTotalCmXCol(), model.getUValorUnitario(), model.getUtilizacao().getId(), model.getEstoque().getId(), model.getUnidadeNegocio().getId(), model.getContaContabil().getId());

		broker.execute();

	}

}
