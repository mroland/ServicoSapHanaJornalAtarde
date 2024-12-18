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
public class HistoricoEasyclassNotaFiscalSaidaLinha extends EasyclassNotaFiscalSaidaLinha implements Serializable{

    public HistoricoEasyclassNotaFiscalSaidaLinha(EasyclassNotaFiscalSaida nota) {
        this.setNotaFiscalSaida(nota);
    }

    public HistoricoEasyclassNotaFiscalSaidaLinha() {

    }



}
