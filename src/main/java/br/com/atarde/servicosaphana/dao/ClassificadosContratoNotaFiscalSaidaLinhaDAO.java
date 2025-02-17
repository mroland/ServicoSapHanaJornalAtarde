package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ClassificadosContratoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(ClassificadosContratoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("classificados_contrato_nff_saida_linha_id_seq"));

		broker.setSQL("INSERT INTO CLASSIFICADOSCONTRATONOTAFISCALSAIDA_LINHASERRO(ID, NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, U_CMXCOL, U_AREA, U_QUANTIDADE_INSERCOES, U_TOTAL_CMXCOL, U_VALOR_UNITARIO, CODIGO_BARRAS, PEDIDO_VENDA_ID, PEDIDO_VENDA_LINHA_NUMERO, UTILIZACAO_ID, VOLUME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(), model.getValor(), model.getCodigoImposto().getId(), model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(), model.getUTotalCmXCol(), model.getUValorUnitario(), model.getCodigoBarras(), model.getPedidoVendaLinha().getPedidoVenda().getId(), model.getPedidoVendaLinha().getNumero(), model.getUtilizacao().getId(), model.getVolume());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ClassificadosContratoNotaFiscalSaidaLinha> pesquisarInterface(ClassificadosContratoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID , ITEM , QUANTIDADE , VALOR_UNITARIO , VALOR , CODIGO_IMPOSTO , CLASSIFICADOS_CONTRATO_NFF_SAIDA_ID , U_CMXCOL , U_AREA , U_QUANTIDADE_INSERCOES , U_TOTAL_CMXCOL , U_VALOR_UNITARIO , UTILIZACAO_ID , DEPOSITO_ID, UNIDADE_NEGOCIO_ID, CONTA_CONTABIL_ID FROM CLASSIFICADOS_CONTRATO_NFF_SAIDA_LINHA S WHERE S.CLASSIFICADOS_CONTRATO_NFF_SAIDA_ID = ?", model.getInterfaceId());

		return broker.getCollectionBean(ClassificadosContratoNotaFiscalSaidaLinha.class, "interfaceId", "item.id", "quantidade", "valorUnitario", "valor", "codigoImposto.id", "notaFiscalSaida.id", "uCmXCol", "uArea", "uQuantidadeInsercoes", "uTotalCmXCol", "uValorUnitario", "utilizacao.id", "estoque.id", "unidadeNegocio.id", "contaContabil.id");
	}

}
