package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class FilialModel implements Serializable {

	@SerializedName("BPLId")
	private Long id;
	
	@SerializedName("BPLName")
	private String descricao;
	
	@SerializedName("MainBPL")
	private Boolean flagPrincipal;
	
	public FilialModel() {
		super();
	}
	
	public FilialModel(Long id) {
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

	public Boolean getFlagPrincipal() {
		return flagPrincipal;
	}

	public void setFlagPrincipal(Boolean flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}

}
