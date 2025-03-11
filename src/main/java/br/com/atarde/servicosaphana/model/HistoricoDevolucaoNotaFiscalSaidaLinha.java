/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaidaLinha;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class HistoricoDevolucaoNotaFiscalSaidaLinha extends DevolucaoNotaFiscalSaidaLinha implements Serializable{

    public HistoricoDevolucaoNotaFiscalSaidaLinha(DevolucaoNotaFiscalSaida nota) {
        this.setDevolucaoNotaFiscalSaida(nota);
    }

    public HistoricoDevolucaoNotaFiscalSaidaLinha(){
        
    }

}

