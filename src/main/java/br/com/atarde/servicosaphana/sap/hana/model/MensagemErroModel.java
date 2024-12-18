package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class MensagemErroModel implements Serializable {

	@SerializedName("lang")
	private String linguagem;

	@SerializedName("value")
	private String valor;

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
