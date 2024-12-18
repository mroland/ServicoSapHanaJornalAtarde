package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CodigoInterno implements Serializable{
	
	private String id;
	private String descricao;
	private Empresa empresa;
	
	public CodigoInterno(){
		
	}
	
	public CodigoInterno(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getDescricaoFormatada(){
		
		return this.id + " - " + this.descricao;
		
	}
	

}
