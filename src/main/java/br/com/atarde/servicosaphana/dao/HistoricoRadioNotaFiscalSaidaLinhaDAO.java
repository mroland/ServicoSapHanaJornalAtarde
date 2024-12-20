package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoRadioNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(RadioNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_radio_nff_saida_linha_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_RADIO_NFF_SAIDA_LINHA (ID,ITEM,QUANTIDADE,VALOR,CODIGO_IMPOSTO,HISTORICO_RADIO_NFF_SAIDA_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, U_SECUNDAGEM, DESCRICAO, UTILIZACAO_ID, DEPOSITO_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValor(), model.getCodigoImposto().getId(), model.getNotaFiscalSaida().getInterfaceId(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getUSecundagem(), model.getDescricao(), model.getUtilizacao().getId(), model.getEstoque().getId());

		broker.execute();

	}

}
