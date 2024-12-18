package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoEasyclassNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(EasyclassNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_easyclass_nff_saida_linha_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_EASYCLASS_NFF_SAIDA_LINHA (ID,ITEM,QUANTIDADE,VALOR_UNITARIO,VALOR,CODIGO_IMPOSTO,HISTORICO_EASYCLASS_NFF_SAIDA_ID,U_CMXCOL,U_AREA,U_QUANTIDADE_INSERCOES,U_TOTAL_CMXCOL,U_VALOR_UNITARIO, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, UTILIZACAO_ID, DEPOSITO_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getNotaFiscalSaida().getInterfaceId(), model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(), model.getUTotalCmXCol(), model.getUValorUnitario(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getUtilizacao().getId(), model.getEstoque().getId());

		broker.execute();

	}

}
