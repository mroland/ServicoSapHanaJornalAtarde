package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoEasyclassPedidoVendaLinhaDAO {

	public void inserirInterface(EasyclassPedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("INSERT INTO INTEGRACAOSISTEMALEGADO.DBO.HISTORICO_EASYCLASS_PEDIDOVENDA_LINHA (ITEM,QUANTIDADE,VALOR_UNITARIO,VALOR,CODIGO_IMPOSTO,HISTORICO_EASYCLASS_NFF_SAIDA_ID,U_CMXCOL,U_AREA,U_QUANTIDADE_INSERCOES,U_TOTAL_CMXCOL,U_VALOR_UNITARIO, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, UTILIZACAO_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getPedidoVenda().getInterfaceId(), model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(), model.getUTotalCmXCol(), model.getUValorUnitario(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getUtilizacao().getId());

		broker.execute();

	}

}
