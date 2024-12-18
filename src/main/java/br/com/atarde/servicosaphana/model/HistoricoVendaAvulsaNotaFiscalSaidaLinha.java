/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class HistoricoVendaAvulsaNotaFiscalSaidaLinha extends VendaAvulsaNotaFiscalSaidaLinha implements Serializable{

    public HistoricoVendaAvulsaNotaFiscalSaidaLinha(VendaAvulsaNotaFiscalSaida nota) {
        this.setNotaFiscalSaida(nota);
    }

    public HistoricoVendaAvulsaNotaFiscalSaidaLinha(){
        
    }

}

