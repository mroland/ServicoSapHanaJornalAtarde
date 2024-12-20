package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ParceiroNegocioModel {

	@SerializedName("Series")
	private Integer serie;

	@SerializedName("CardCode")
	private String id;

	@SerializedName("CardName")
	private String nome;

	@SerializedName("GroupCode")
	private Integer classificacao;

	@SerializedName("CardType")
	private String tipo;

	@SerializedName("CardForeignName")
	private String nomeFantasia;

	@SerializedName("Phone1")
	private String telefoneResidencial;

	@SerializedName("Cellular")
	private String telefoneCelular;

	@SerializedName("Fax")
	private String fax;

	@SerializedName("EmailAddress")
	private String email;

	@SerializedName("FreeText")
	private String observacao;

	@SerializedName("BPPaymentMethods")
	private List<FormaPagamentoModel> formasPagamentos;

	@SerializedName("BPFiscalTaxIDCollection")
	private List<IdentificadorFiscalModel> identificadoresFiscais;

	@SerializedName("BPAddresses")
	private List<ParceiroNegocioEnderecoModel> enderecos;

	@SerializedName("BPBranchAssignment")
	private List<FilialParceiroNegocioModel> filiais;

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

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<FormaPagamentoModel> getFormasPagamentos() {
		return formasPagamentos;
	}

	public void setFormasPagamentos(List<FormaPagamentoModel> formasPagamentos) {
		this.formasPagamentos = formasPagamentos;
	}

	public List<IdentificadorFiscalModel> getIdentificadoresFiscais() {
		return identificadoresFiscais;
	}

	public void setIdentificadoresFiscais(List<IdentificadorFiscalModel> identificadoresFiscais) {
		this.identificadoresFiscais = identificadoresFiscais;
	}

	public List<ParceiroNegocioEnderecoModel> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<ParceiroNegocioEnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}

	public List<FilialParceiroNegocioModel> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<FilialParceiroNegocioModel> filiais) {
		this.filiais = filiais;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

}
