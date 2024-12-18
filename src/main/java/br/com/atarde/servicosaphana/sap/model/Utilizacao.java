package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Utilizacao implements Serializable{
	
	//Tabela OUSG
	
	private Long id;
	private String descricao;
	private Empresa empresa;
	
	public Utilizacao(){
		
	}
	
	public Utilizacao(Long id, Empresa empresa) {

		this.id = id;
		
		this.empresa = empresa;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	

}
