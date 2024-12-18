package br.com.atarde.servicosaphana.sap.dao;

import java.util.List;

import br.com.atarde.servicosaphana.sap.model.ParcelaNotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ParcelaNotaFiscalSaidaDAO {

    @SuppressWarnings("unchecked")
    public List<ParcelaNotaFiscalSaida> pesquisar(ParcelaNotaFiscalSaida model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("parcelanotafiscalsaidadao.pesquisar", model.getContasReceberLinha().getContasReceber().getId());

        return broker.getCollectionBean(ParcelaNotaFiscalSaida.class, "id", "numero", "valorAberto");
    }

}
