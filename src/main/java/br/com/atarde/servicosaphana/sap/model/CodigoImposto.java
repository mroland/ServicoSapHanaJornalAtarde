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
public class CodigoImposto implements Serializable{

    // tabela OSTC

    private String id;          //equivalente a code

    private String descricao;   //equivalente a name
    
    private Empresa empresa; 
    
    public CodigoImposto(){
    	
    }
    
    public CodigoImposto(String atributo, String valor, Empresa empresa){
    	
    	if("id".equals(atributo)){
    		
    		this.id = valor;
    		
    		this.empresa = empresa;
    		
    	}
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
