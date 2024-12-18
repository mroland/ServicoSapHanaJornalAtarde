/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParceiroNegocioEndereco extends EnderecoAB implements Serializable {

    private ParceiroNegocio parceiroNegocio;
    private String tipoEndereco;
    
    public ParceiroNegocioEndereco(){
    	
    }
    
	public ParceiroNegocioEndereco(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}
	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}
	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}
	public String getTipoEndereco() {
		return tipoEndereco;
	}
	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}    
}
