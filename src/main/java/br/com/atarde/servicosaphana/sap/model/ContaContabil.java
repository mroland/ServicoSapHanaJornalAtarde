package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContaContabil implements Serializable{
	
    // tabela OACT

    private String id;

    private String descricao;
    
    private Empresa empresa; 
    
    private OrigemExtrato origemExtrato;
    
    private Banco banco;    
    
    public ContaContabil(){
    	
    }

    public ContaContabil(String id) {

    	this.id = id;
    	
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

	public OrigemExtrato getOrigemExtrato() {
		return origemExtrato;
	}

	public void setOrigemExtrato(OrigemExtrato origemExtrato) {
		this.origemExtrato = origemExtrato;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
    
    

}
