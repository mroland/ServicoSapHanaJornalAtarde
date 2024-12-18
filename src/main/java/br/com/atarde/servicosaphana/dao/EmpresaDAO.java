package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EmpresaDAO {

	public Empresa obter(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO, JNDI, SERVIDOR, DB_INSTANCIA, DB_USUARIO, DB_SENHA, APP_USUARIO, APP_SENHA, SERVIDOR_LICENCA, PORTA_LICENCA, CNPJ, URL_SAP_HANA FROM EMPRESAS WHERE ID= ?", model.getId());

		return (Empresa) broker.getObjectBean(Empresa.class, "id", "descricao", "flagAtivo", "jndi", "servidor", "dbInstancia", "dbUsuario", "dbSenha", "appUsuario", "appSenha", "servidorLicenca", "portaLicenca", "cnpj", "urlSapHana");

	}

}
