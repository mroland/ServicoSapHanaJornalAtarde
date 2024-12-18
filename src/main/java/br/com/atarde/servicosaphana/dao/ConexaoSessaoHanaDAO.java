package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ConexaoSessaoHanaDAO {

	public ConexaoSessaoHanaModel obter(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);
		
		broker.setSQL("SELECT ID, EMPRESA_ID, SESSAO_ID, SESSAO_TIMEOUT, DATA_INICIAL, DATA_EXPIRACAO FROM PUBLIC.CONEXAO_SESSAO WHERE EMPRESA_ID = ?", model.getId());
		
		return (ConexaoSessaoHanaModel) broker.getObjectBean(ConexaoSessaoHanaModel.class, "id", "empresaModel.id", "sessaoId", "sessaoTimeout", "dataInicial", "dataExpiracao");
	}

	public ConexaoSessaoHanaModel inserir(ConexaoSessaoHanaModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);
		
		model.setId(broker.getSequenceNextValue("conexao_sessao_id_seq"));
		
		broker.setSQL("INSERT INTO PUBLIC.CONEXAO_SESSAO (ID, EMPRESA_ID, SESSAO_ID, SESSAO_TIMEOUT, DATA_INICIAL, DATA_EXPIRACAO) VALUES(?, ?, ?, ?, ?, ?)", model.getId(), model.getEmpresaModel().getId(), model.getSessaoId(), model.getSessaoTimeout(), new Timestamp(model.getDataInicial().getTime()), new Timestamp(model.getDataExpiracao().getTime()));
	
		broker.execute();
		
		return model;
		
	}

	public ConexaoSessaoHanaModel alterar(ConexaoSessaoHanaModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);
		
		broker.setSQL("UPDATE PUBLIC.CONEXAO_SESSAO SET SESSAO_ID = ?, SESSAO_TIMEOUT = ?, DATA_INICIAL = ?, DATA_EXPIRACAO = ? WHERE ID = ?", model.getSessaoId(), model.getSessaoTimeout(), new Timestamp(model.getDataInicial().getTime()), new Timestamp(model.getDataExpiracao().getTime()), model.getId());
	
		broker.execute();
		
		return model;
		
	}

}
