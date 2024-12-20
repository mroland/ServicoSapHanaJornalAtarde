package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class RadioNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(RadioNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("easyclassnotafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO RADIO_NFF_SAIDA_LINHA(ID, NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, U_CMXCOL, U_AREA, U_QUANTIDADE_INSERCOES, U_TOTAL_CMXCOL, U_VALOR_UNITARIO, CODIGO_BARRAS, PEDIDO_VENDA_ID, PEDIDO_VENDA_LINHA_NUMERO, UTILIZACAO_ID, VOLUME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getCodigoBarras(), model.getUtilizacao().getId(), model.getVolume());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<RadioNotaFiscalSaidaLinha> pesquisarInterface(RadioNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ITEM , QUANTIDADE , VALOR , CODIGO_IMPOSTO , CST_ICMS, CST_PIS, CST_IPI, CST_COFINS, U_SECUNDAGEM, DESCRICAO, UTILIZACAO_ID , DEPOSITO_ID FROM RADIO_NFF_SAIDA_LINHA WHERE RADIO_NFF_SAIDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(RadioNotaFiscalSaidaLinha.class, "interfaceId", "item.id", "quantidade", "valor", "codigoImposto.id", "cstICMS.codigo", "cstPIS.codigo", "cstIPI.codigo", "cstCOFINS.codigo", "uSecundagem", "descricao", "utilizacao.id", "estoque.id");
	}

}
