package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosContratoPedidoVenda;
import br.com.atarde.servicosaphana.model.ClassificadosContratoPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ClassificadosContratoPedidoVendaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<ClassificadosContratoPedidoVendaLinha> pesquisarInterface(ClassificadosContratoPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID , ITEM , QUANTIDADE , VALOR_UNITARIO , VALOR , CODIGO_IMPOSTO , CLASSIFICADOS_CONTRATO_PEDIDOVENDA_ID , U_CMXCOL , U_AREA , U_QUANTIDADE_INSERCOES , U_TOTAL_CMXCOL , U_VALOR_UNITARIO , UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID FROM CLASSIFICADOS_CONTRATO_PEDIDOVENDA_LINHA S WHERE S.CLASSIFICADOS_CONTRATO_PEDIDOVENDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(ClassificadosContratoPedidoVendaLinha.class, "interfaceId", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "pedidoVenda.id", "uCmXCol", "uArea", "uQuantidadeInsercoes", "uTotalCmXCol", "uValorUnitario", "utilizacao.id", "estoque.id", "unidadeNegocio.id", "contaContabil.id");
	}

}
