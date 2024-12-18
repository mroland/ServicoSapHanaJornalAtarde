package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class EasyclassNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(EasyclassNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("easyclassnotafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO EASYCLASSNOTAFISCALSAIDA_LINHAS(ID, NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, U_CMXCOL, U_AREA, U_QUANTIDADE_INSERCOES, U_TOTAL_CMXCOL, U_VALOR_UNITARIO, CODIGO_BARRAS, PEDIDO_VENDA_ID, PEDIDO_VENDA_LINHA_NUMERO, UTILIZACAO_ID, VOLUME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), 
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
	public List<EasyclassNotaFiscalSaidaLinha> pesquisarInterface(EasyclassNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, ITEM , QUANTIDADE , VALOR_UNITARIO , VALOR , CODIGO_IMPOSTO , U_CMXCOL , U_AREA , U_QUANTIDADE_INSERCOES , U_TOTAL_CMXCOL , U_VALOR_UNITARIO , CST_ICMS, CST_PIS, CST_IPI, CST_COFINS, UTILIZACAO_ID , DEPOSITO_ID FROM EASYCLASS_NFF_SAIDA_LINHA WHERE EASYCLASS_NFF_SAIDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(EasyclassNotaFiscalSaidaLinha.class, "interfaceId", "item.id", "quantidade", "valorUnitario",
				                                                             "valor","codigoImposto.id", "uCmXCol", "uArea", 
				                                                             "uQuantidadeInsercoes", "uTotalCmXCol", "uValorUnitario", 
				                                                             "cstICMS.codigo", "cstPIS.codigo", "cstIPI.codigo", "cstCOFINS.codigo", 
				                                                             "utilizacao.id", "estoque.id");
	}

}
