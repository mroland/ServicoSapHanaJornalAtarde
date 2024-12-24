package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Filial implements Serializable {

	private Integer id;
	private String descricao;
	private Boolean flagPrincipal;
	private Empresa empresa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFlagPrincipal() {
		return flagPrincipal;
	}

	public void setFlagPrincipal(Boolean flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}

}
