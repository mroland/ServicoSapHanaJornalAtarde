package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Banco implements Serializable {
	
	private Long id;
	private String descricao;
	private String codigo;
	private Conta conta;
	private String flagExtrato;
	private Empresa empresa;	
	
	public Banco() {
	
	}
	
	public Banco(Conta conta) {
		
		this.conta = conta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banco other = (Banco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getFlagExtrato() {
		return flagExtrato;
	}

	public void setFlagExtrato(String flagExtrato) {
		this.flagExtrato = flagExtrato;
	}

}
