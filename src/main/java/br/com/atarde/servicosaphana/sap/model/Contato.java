package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contato implements Serializable{
	
	private String id;
	
	private String nome;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
