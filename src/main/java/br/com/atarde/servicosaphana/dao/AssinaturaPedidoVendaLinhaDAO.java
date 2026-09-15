package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.AssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class AssinaturaPedidoVendaLinhaDAO {

	public AssinaturaPedidoVendaLinha inserirInterface(AssinaturaPedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("assinaturapedidovenda_linhas_id_seq"));

		broker.setSQL("INSERT INTO ASSINATURAPEDIDOVENDA_LINHAS(ID, PEDIDOVENDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getPedidoVenda().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getContaContabil().getId(), model.getCfop().getCodigo(), model.getCodigoBarras(), model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto());

		broker.execute();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<AssinaturaPedidoVendaLinha> pesquisarInterface(AssinaturaPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, PEDIDOVENDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO, DEPOSITO_ID, UNIDADE_NEGOCIO_ID FROM ASSINATURAPEDIDOVENDA_LINHAS WHERE PEDIDOVENDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(AssinaturaPedidoVendaLinha.class, "interfaceId", "pedidoVenda.interfaceId", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "cstCOFINS.codigo", "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", "contaContabil.id", "cfop.codigo", "codigoBarras", "utilizacao.id", "volume", "flagImposto", "estoque.id", "unidadeNegocio.id");
	}

}
