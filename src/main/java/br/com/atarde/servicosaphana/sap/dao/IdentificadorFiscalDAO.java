/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class IdentificadorFiscalDAO {

    public IdentificadorFiscalDAO() {
    }

    public IdentificadorFiscal obterEnderecoNulo(IdentificadorFiscal model){

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setSQL("SELECT CRD7.\"Address\" FROM " + model.getEmpresa().getDbInstancia() +  ".CRD7 CRD7 WHERE CRD7.\"CardCode\" = ? AND CRD7.\"Address\" = ?", model.getParceiroNegocio().getId(),model.getEnderecoId());

        return (IdentificadorFiscal) broker.getObjectBean(IdentificadorFiscal.class, "enderecoId");
        
    }

}
