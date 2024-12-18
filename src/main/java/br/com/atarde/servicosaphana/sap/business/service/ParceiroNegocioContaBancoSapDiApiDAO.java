/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioContaBanco;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioContaBancoSapDiApiDAO {

    private int retorno;

    public ParceiroNegocioContaBancoSapDiApiDAO() throws TSApplicationException {

        this.initObjetosNaRequisicao();


    }

    public ParceiroNegocioContaBanco inserir(ParceiroNegocioContaBanco model) throws TSApplicationException {

      
        return model;
    }
    private void initObjetosNaRequisicao() throws TSApplicationException {
    	
    }    
}
