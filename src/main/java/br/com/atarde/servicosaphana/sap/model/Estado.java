package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Estado implements Serializable{
	
	private String id;
	
	private String descricao;
	
	private Pais pais;
	
	private Empresa empresa;
	
	public Estado(){
		
	}

	public Estado(String id, Empresa empresa) {

		this.id = id;
		
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

}
