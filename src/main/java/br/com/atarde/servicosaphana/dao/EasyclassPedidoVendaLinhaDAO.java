package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class EasyclassPedidoVendaLinhaDAO {

	public void inserirInterface(EasyclassPedidoVendaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("easyclasspedidovenda_linhas_id_seq"));

		broker.setSQL("INSERT INTO EASYCLASSPEDIDOVENDA_LINHAS(ID, PEDIDOVENDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, U_CMXCOL, U_AREA, U_QUANTIDADE_INSERCOES, U_TOTAL_CMXCOL, U_VALOR_UNITARIO, CODIGO_BARRAS, PEDIDO_VENDA_ID, PEDIDO_VENDA_LINHA_NUMERO, UTILIZACAO_ID, VOLUME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getPedidoVenda().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(),
				                                                            model.getUTotalCmXCol(), model.getUValorUnitario(),
				                                                            model.getCodigoBarras(), model.getPedidoVendaLinha().getPedidoVenda().getId(),
				                                                            model.getPedidoVendaLinha().getNumero(),
				                                                            model.getUtilizacao().getId(), model.getVolume());

		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<EasyclassPedidoVendaLinha> pesquisarInterface(EasyclassPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID,ITEM,QUANTIDADE,VALOR_UNITARIO,VALOR,CODIGO_IMPOSTO,U_CMXCOL,U_AREA,U_QUANTIDADE_INSERCOES,U_TOTAL_CMXCOL,U_VALOR_UNITARIO, CST_ICMS, CST_PIS, CST_IPI,CST_COFINS, UTILIZACAO_ID  FROM INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA_LINHA WITH(NOLOCK) WHERE EASYCLASS_PEDIDOVENDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(EasyclassPedidoVendaLinha.class, "interfaceId", "item.id", "quantidade", "valorUnitario",
				                                                             "valor","codigoImposto.id", "uCmXCol", "uArea", 
				                                                             "uQuantidadeInsercoes", "uTotalCmXCol", "uValorUnitario", 
				                                                             "cstICMSModel.codigo", "cstPIS.codigo", "cstIPI.codigo", "cstCOFINS.codigo", 
				                                                             "utilizacao.id");
	}

}
