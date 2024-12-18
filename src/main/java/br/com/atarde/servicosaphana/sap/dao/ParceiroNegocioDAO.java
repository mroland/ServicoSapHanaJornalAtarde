/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioDAO {

	public ParceiroNegocioDAO() {
	}

	public ParceiroNegocio obterPorIdentificador(ParceiroNegocio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT CRD7.\"CardCode\", OCRD.\"UpdateDate\", OCRD.\"ShipToDef\", OCRD.\"BillToDef\"  ");

		sql.append(" FROM " + model.getEmpresa().getDbInstancia() + ".CRD7, " + model.getEmpresa().getDbInstancia() + ".OCRD");

		sql.append(" WHERE (CRD7.\"CardCode\" = OCRD.\"CardCode\") AND (CRD7.\"Address\" = '' OR CRD7.\"Address\" IS NULL) ");

		sql.append(" AND OCRD.\"CardType\" = 'C'");

		switch (model.getIdentificadorFiscal().getTipoIdentificador().intValue()) {

		case 0: // CNPJ

			sql.append(" AND REPLACE(REPLACE(REPLACE(CRD7.\"TaxId0\", '.',''), '/',''), '-','')= ?");

			break;

		case 1: // CPF

			sql.append(" AND REPLACE(REPLACE(REPLACE(CRD7.\"TaxId4\", '.',''), '/',''), '-','')= ?");

			break;

		case 2: // OUTROS

			sql.append(" AND REPLACE(REPLACE(REPLACE(CRD7.\"TaxId5\", '.',''), '/',''), '-','')= ?");

			break;

		}

		broker.setSQL(sql.toString());

		broker.set(model.getIdentificadorFiscal().getIdentificador());

		return (ParceiroNegocio) broker.getObjectBean(ParceiroNegocio.class, "id", "dataAtualizacao", "enderecoEntregaDefault", "enderecoCobrancaDefault");

	}

	public ParceiroNegocio obter(ParceiroNegocio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OCRD.\"CardCode\", OCRD.\"ShipToDef\" AS ENDERECO_ENTREGA, OCRD.\"BillToDef\" AS ENDERECO_COBRANCA, CASE WHEN (OCRD.\"validFor\"='Y' OR (OCRD.\"validFor\"='N' AND OCRD.\"frozenFor\"='N')) THEN true ELSE false END AS FLAG_ATIVO, OCRD.\"U_ATRD_DocSai\" FROM " + model.getEmpresa().getDbInstancia() + ".OCRD WHERE OCRD.\"CardCode\" = ?", model.getId());

		return (ParceiroNegocio) broker.getObjectBean(ParceiroNegocio.class, "id", "enderecoEntregaDefault", "enderecoCobrancaDefault", "flagAtivo", "uTipoDocumento");
	}

	public ParceiroNegocio obterTesteSAP() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf("java:comp/env/jdbc/SAPJornalHanaDS");

		broker.setSQL("SELECT \"CardCode\" , \"CardName\"  FROM SBODEMOBR.OCRD o where \"CardCode\" = ?" , "C30000");

		return (ParceiroNegocio) broker.getObjectBean(ParceiroNegocio.class, "id", "nome");

	}

}
