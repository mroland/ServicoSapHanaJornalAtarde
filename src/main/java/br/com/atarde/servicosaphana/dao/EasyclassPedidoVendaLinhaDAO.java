package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EasyclassPedidoVendaLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<EasyclassPedidoVendaLinha> pesquisarInterface(EasyclassPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, ITEM , QUANTIDADE , VALOR_UNITARIO , VALOR , CODIGO_IMPOSTO , U_CMXCOL , U_AREA , U_QUANTIDADE_INSERCOES , U_TOTAL_CMXCOL , U_VALOR_UNITARIO , CST_ICMS, CST_PIS, CST_IPI, CST_COFINS, UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID FROM EASYCLASS_PEDIDOVENDA_LINHA WHERE EASYCLASS_PEDIDOVENDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(EasyclassPedidoVendaLinha.class, "interfaceId", "item.id", "quantidade", "valorUnitario",
				                                                             "valor","codigoImposto.id", "uCmXCol", "uArea", 
				                                                             "uQuantidadeInsercoes", "uTotalCmXCol", "uValorUnitario", 
				                                                             "cstICMS.codigo", "cstPIS.codigo", "cstIPI.codigo", "cstCOFINS.codigo", 
				                                                             "utilizacao.id", "estoque.id", "unidadeNegocio.id", "contaContabil.id");
	}

}
