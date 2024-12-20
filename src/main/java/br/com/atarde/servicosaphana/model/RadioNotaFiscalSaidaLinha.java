/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class RadioNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable{

    private Integer uSecundagem;
    private String descricao;
    private RadioNotaFiscalSaida notaFiscalSaida;

    public RadioNotaFiscalSaidaLinha(RadioNotaFiscalSaida model) {
        this.notaFiscalSaida = model;
    }
    
    public RadioNotaFiscalSaidaLinha(){
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
	public Integer getUSecundagem() {
        return uSecundagem;
    }

    public void setUSecundagem(Integer uSecundagem) {
        this.uSecundagem = uSecundagem;
    }

    public RadioNotaFiscalSaida getNotaFiscalSaida() {
        return notaFiscalSaida;
    }

    public void setNotaFiscalSaida(RadioNotaFiscalSaida notaFiscalSaida) {
        this.notaFiscalSaida = notaFiscalSaida;
    }
}
