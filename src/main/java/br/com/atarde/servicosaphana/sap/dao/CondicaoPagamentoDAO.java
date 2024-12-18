package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.CondicaoPagamento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CondicaoPagamentoDAO {

	public CondicaoPagamento obter(CondicaoPagamento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCTG.\"GroupNum\", OCTG.\"InstNum\" FROM" + model.getEmpresa().getDbInstancia() + ".OCTG WHERE OCTG.\"GroupNum\" = ?", model.getId());

		return (CondicaoPagamento) broker.getObjectBean(CondicaoPagamento.class, "id", "quantidadeParcelas");
	}

	@SuppressWarnings("unchecked")
	public List<CondicaoPagamento> pesquisar(CondicaoPagamento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setPropertySQL("SELECT OCTG.\"GroupNum\", OCTG.\"PymntGroup\", CASE WHEN COALESCE((SELECT MAX(CTG1.\"InstDays\") FROM " + model.getEmpresa().getDbInstancia() + ".CTG1 WHERE CTG1.\"CTGCode\"=OCTG.\"GroupNum\"),'0') = '0' THEN OCTG.\"ExtraDays\" ELSE (SELECT MAX(CTG1.\"InstDays\") FROM " + model.getEmpresa().getDbInstancia() + ".CTG1 WHERE CTG1.\"CTGCode\"=OCTG.\"GroupNum\") END AS DIASVENCIMENTO FROM " + model.getEmpresa().getDbInstancia() + ".OCTG");// WHERE OCTG.\"U_EXPORTAR\" = 1");

		return broker.getCollectionBean(CondicaoPagamento.class, "id", "descricao", "diasVencimento");

	}

}
