package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import br.com.atarde.servicosaphana.sap.model.Empresa;

@SuppressWarnings("serial")
public class ParceiroNegocioEnderecoModel implements Serializable {

	@SerializedName("AddressName")
	private String id;

	@SerializedName("AddressType")
	private String tipoEndereco;

	@SerializedName("Street")
	private String logradouro;

	@SerializedName("Block")
	private String bairro;

	@SerializedName("StreetNo")
	private String numero;

	@SerializedName("City")
	private String cidade;

	@SerializedName("Country")
	private String pais;

	@SerializedName("State")
	private String estado;

	@SerializedName("BuildingFloorRoom")
	private String complemento;

	@SerializedName("ZipCode")
	private String cep;

	@SerializedName("County")
	private String municipio;

	@SerializedName("TypeOfAddress")
	private String tipoLogradouro;

	@SerializedName("BPCode")
	private String paceiroNegocioId;

	private Empresa empresa;

	@SerializedName("CreateDate")
	private String dataCriacao;

	@SerializedName("CreateTime")
	private String horaCriacao;

	@SerializedName("RowNum")
	private Integer linha;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getPaceiroNegocioId() {
		return paceiroNegocioId;
	}

	public void setPaceiroNegocioId(String paceiroNegocioId) {
		this.paceiroNegocioId = paceiroNegocioId;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(String horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

}
