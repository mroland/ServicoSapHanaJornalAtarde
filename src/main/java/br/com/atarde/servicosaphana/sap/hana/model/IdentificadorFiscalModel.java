package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import br.com.atarde.servicosaphana.sap.model.Empresa;

@SuppressWarnings("serial")
public class IdentificadorFiscalModel implements Serializable {

	// Tabela CRD7

	private String id;

	@SerializedName("Address")
	private String enderecoId;

	@SerializedName("TaxId0")
	private String cnpj;

	@SerializedName("TaxId4")
	private String cpf;

	@SerializedName("TaxId5")
	private String outros;

	@SerializedName("TaxId1")
	private String inscricaoEstadual;

	@SerializedName("TaxId2")
	private String inscricaoEstadualSubstitutoTributaria;

	@SerializedName("TaxId3")
	private String inscricaoMunicipal;

	@SerializedName("TaxId7")
	private String inscricaoINSS;

	@SerializedName("AddrType")
	private String tipoEndereco;

	@SerializedName("BPCode")
	private String parceiroNegocioId;

	private Empresa empresa;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(String enderecoId) {
		this.enderecoId = enderecoId;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoEstadualSubstitutoTributaria() {
		return inscricaoEstadualSubstitutoTributaria;
	}

	public void setInscricaoEstadualSubstitutoTributaria(String inscricaoEstadualSubstitutoTributaria) {
		this.inscricaoEstadualSubstitutoTributaria = inscricaoEstadualSubstitutoTributaria;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoINSS() {
		return inscricaoINSS;
	}

	public void setInscricaoINSS(String inscricaoINSS) {
		this.inscricaoINSS = inscricaoINSS;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getParceiroNegocioId() {
		return parceiroNegocioId;
	}

	public void setParceiroNegocioId(String parceiroNegocioId) {
		this.parceiroNegocioId = parceiroNegocioId;
	}

}
