package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoAssinaturaNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(AssinaturaNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturanotafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_ASSINATURANOTAFISCALSAIDA_LINHAS(ID, HISTORICO_NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, PEDIDO_VENDA_ID, PEDIDO_VENDA_LINHA_NUMERO, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getContaContabil().getId(),model.getCfop().getCodigo(),
				                                                            model.getCodigoBarras(), model.getPedidoVendaLinha().getPedidoVenda().getId(),
				                                                            model.getPedidoVendaLinha().getNumero(),
				                                                            model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto());

		broker.execute();

		
	}

}
