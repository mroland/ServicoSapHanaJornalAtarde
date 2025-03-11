package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoDevolucaoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(DevolucaoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_devolucao_notafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_DEVOLUCAO_NOTAFISCALSAIDA_LINHAS(ID, HISTORICO_DEVOLUCAO_NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO, DEPOSITO_ID, UNIDADE_NEGOCIO_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getDevolucaoNotaFiscalSaida().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getContaContabil().getId(), model.getCfop().getCodigo(), model.getCodigoBarras(), model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto(), model.getEstoque().getId(), model.getUnidadeNegocio().getId());

		broker.execute();

	}

}
