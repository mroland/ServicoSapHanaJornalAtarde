package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.business.TabelaUsuarioImpostoTaxa;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Filial;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class TabelaUsuarioImpostoTaxaDAO {

	@SuppressWarnings("unchecked")
	public List<TabelaUsuarioImpostoTaxa> obterPorCliente(Empresa empresa, Filial filial, String clienteId, Long codigoImpostoCliente) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(empresa.getJndi());

		broker.setSQL("SELECT A.\"WTCode\", D.\"Rate\" FROM " + empresa.getDbInstancia() + ".TCD4 A INNER JOIN " + empresa.getDbInstancia() + ".TCD2 B ON B.\"AbsId\" = A.\"Tcd2Id\" INNER JOIN " + empresa.getDbInstancia() + ".OWHT D ON D.\"WTCode\" = A.\"WTCode\" WHERE B.\"Tcd1Id\" = ? AND B.\"KeyFld_1_V\" = ? AND B.\"KeyFld_2_V\" = ?", codigoImpostoCliente, filial.getId(), clienteId);

		return broker.getCollectionBean(TabelaUsuarioImpostoTaxa.class, "impostoId", "percentual");

	}

	@SuppressWarnings("unchecked")
	public List<TabelaUsuarioImpostoTaxa> obterPorGeral(Empresa empresa, Filial filial, Long municipioId, Long classificacaoId, Long codigoImpostoCliente, Long sequenciaId) { 

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(empresa.getJndi());

		broker.setSQL("SELECT A.\"WTCode\", D.\"Rate\" FROM " + empresa.getDbInstancia() + ".TCD4 A INNER JOIN " + empresa.getDbInstancia() + ".TCD2 B ON B.\"AbsId\" = A.\"Tcd2Id\" INNER JOIN " + empresa.getDbInstancia() + ".OWHT D ON D.\"WTCode\" = A.\"WTCode\" WHERE B.\"Tcd1Id\" = ? AND B.\"KeyFld_1_V\" = ? AND B.\"KeyFld_2_V\" = ? AND B.\"KeyFld_3_V\" =? AND B.\"KeyFld_4_V\" = ?", codigoImpostoCliente, filial.getId(), municipioId, classificacaoId, sequenciaId);

		return broker.getCollectionBean(TabelaUsuarioImpostoTaxa.class, "impostoId", "percentual");
	}

}
