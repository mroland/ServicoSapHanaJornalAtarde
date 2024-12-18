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
public class HistoricoClassificadosExecucaoNotaFiscalSaidaLinha extends ClassificadosExecucaoNotaFiscalSaidaLinha implements Serializable{

    public HistoricoClassificadosExecucaoNotaFiscalSaidaLinha(ClassificadosExecucaoNotaFiscalSaida nota) {
        this.setNotaFiscalSaida(nota);
    }

    public HistoricoClassificadosExecucaoNotaFiscalSaidaLinha() {

    }



}
