package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.RadioPedidoVenda;
import br.com.atarde.servicosaphana.model.RadioPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class RadioPedidoVendaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<RadioPedidoVendaLinha> pesquisarInterface(RadioPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, ITEM , QUANTIDADE , VALOR , CODIGO_IMPOSTO , CST_ICMS, CST_PIS, CST_IPI, CST_COFINS, U_SECUNDAGEM, DESCRICAO, UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID FROM RADIO_PEDIDOVENDA_LINHA WHERE RADIO_PEDIDOVENDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(RadioPedidoVendaLinha.class, "interfaceId", "item.id", "quantidade", "valor", "codigoImposto.id", "cstICMS.codigo", "cstPIS.codigo", "cstIPI.codigo", "cstCOFINS.codigo", "uSecundagem", "descricao", "utilizacao.id", "estoque.id", "unidadeNegocio.id", "contaContabil.id");
	}

}
