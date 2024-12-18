package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.Filial;
import br.com.atarde.servicosaphana.sap.model.Sequencia;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class SequenciaDAO {

	public Sequencia obter(Sequencia model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT NFN1.\"SeqCode\" FROM " + model.getEmpresa().getDbInstancia() + ".NFN1 WHERE NFN1.\"SeqCode\" = ?", model.getId());

		return (Sequencia) broker.getObjectBean(Sequencia.class, "id");
	}

	public Sequencia obterInterface(Integer uTipoDocumento, Filial filial) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);

		broker.setSQL("SELECT ID, FILIAL_ID, ID_EXTERNO, TIPO_DOCUMENTO FROM SEQUENCIA S WHERE FILIAL_ID = ? AND TIPO_DOCUMENTO  = ?", filial.getId(), uTipoDocumento);

		return (Sequencia) broker.getObjectBean(Sequencia.class, "id", "filial.id", "idExterno", "tipoDocumento");
		
	}

}
