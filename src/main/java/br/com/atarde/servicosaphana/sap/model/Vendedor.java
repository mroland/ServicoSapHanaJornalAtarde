/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class Vendedor implements Serializable {

    private Long id;
    private String nome;
    private String identificador;
    private Integer tipoIdentificador;     
    private Empresa empresa;
    private GrupoComissao grupoComissao;
    private String mensagemErro;
    private VendedorEndereco endereco; 
    private Date dataAtualizacao;  
    private String uCga;
    
    public Vendedor(){
    	
    }

    public Vendedor(Empresa empresa) {

    	this.empresa = empresa;
    	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public GrupoComissao getGrupoComissao() {
		return grupoComissao;
	}

	public void setGrupoComissao(GrupoComissao grupoComissao) {
		this.grupoComissao = grupoComissao;
	}

	public VendedorEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(VendedorEndereco endereco) {
		this.endereco = endereco;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Integer getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Integer tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public String getUCga() {
		return uCga;
	}

	public void setUCga(String uCga) {
		this.uCga = uCga;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
    
}
