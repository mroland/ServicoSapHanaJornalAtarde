package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class DevolucaoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(DevolucaoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("devolucao_notafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO DEVOLUCAO_NOTAFISCALSAIDA_LINHAS(ID, DEVOLUCAO_NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getDevolucaoNotaFiscalSaida().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getContaContabil().getId(),model.getCfop().getCodigo(),
				                                                            model.getCodigoBarras(), 
				                                                            model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto());

		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<DevolucaoNotaFiscalSaidaLinha> pesquisarInterface(DevolucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DEVOLUCAO_NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO, DEPOSITO_ID, UNIDADE_NEGOCIO_ID FROM DEVOLUCAO_NOTAFISCALSAIDA_LINHAS WHERE DEVOLUCAO_NOTAFISCALSAIDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(DevolucaoNotaFiscalSaidaLinha.class, "interfaceId", "devolucaoNotaFiscalSaida.interfaceId", "item.id", "quantidade",
																			   "valorUnitario", "valor", "codigoImposto.id","cstCOFINS.codigo",
																			   "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", "contaContabil.id",
																			   "cfop.codigo", "codigoBarras", "utilizacao.id", "volume", "flagImposto", "estoque.id", "unidadeNegocio.id");
	}

}
