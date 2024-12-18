package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrigemExtrato implements Serializable {

	private Long id;

	private String descricao;
	
	private Empresa empresa;

	public OrigemExtrato(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public OrigemExtrato(){
		
	}

	public OrigemExtrato(long id) {
		this.id = id;
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