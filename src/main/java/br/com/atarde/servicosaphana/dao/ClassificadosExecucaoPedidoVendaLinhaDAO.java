package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVenda;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ClassificadosExecucaoPedidoVendaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<ClassificadosExecucaoPedidoVendaLinha> pesquisarInterface(ClassificadosExecucaoPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID , ITEM , QUANTIDADE , VALOR , CODIGO_IMPOSTO , CLASSIFICADOS_EXECUCAO_PEDIDOVENDA_ID , UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID FROM CLASSIFICADOS_EXECUCAO_PEDIDOVENDA_LINHA S WHERE S.CLASSIFICADOS_EXECUCAO_PEDIDOVENDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(ClassificadosExecucaoPedidoVendaLinha.class, "interfaceId", "item.id", "quantidade", "valor", "codigoImposto.id", "pedidoVenda.id", "utilizacao.id", "estoque.id", "unidadeNegocio.id", "contaContabil.id");
	}
	

}
