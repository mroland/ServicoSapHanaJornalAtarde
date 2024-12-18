package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class ConexaoSapHanaModel implements Serializable {

	@SerializedName("CompanyDB")
	private String instancia;

	@SerializedName("UserName")
	private String usuario;

	@SerializedName("Password")
	private String senha;

	@SerializedName("Language")
	private Integer linguagem;

	public String getInstancia() {
		return instancia;
	}

	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Integer linguagem) {
		this.linguagem = linguagem;
	}

}
