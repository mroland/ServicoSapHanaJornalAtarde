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
public class HistoricoFotografiaNotaFiscalSaidaLinha extends FotografiaNotaFiscalSaidaLinha implements Serializable{

    public HistoricoFotografiaNotaFiscalSaidaLinha(FotografiaNotaFiscalSaida nota) {
        this.setNotaFiscalSaida(nota);
    }

    public HistoricoFotografiaNotaFiscalSaidaLinha(){
        
    }

}

