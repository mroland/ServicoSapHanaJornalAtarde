package br.com.atarde.servicosaphana.sap.hana.model;

import com.google.gson.annotations.SerializedName;

public class VendedorModel {

	@SerializedName("SalesEmployeeCode")
	private String id;

	@SerializedName("SalesEmployeeName")
	private String nome;

	@SerializedName("CommissionForSalesEmployee")
	private Double percentualComissao;

	@SerializedName("CommissionGroup")
	private Integer grupoComissao;

	@SerializedName("Remarks")
	private String identificador;

	@SerializedName("U_ATRD_CNPJAg")
	private String uCga;

	@SerializedName("U_ATRD_Agrupador")
	private String uAgrupador;

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

	public Double getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(Double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getuCga() {
		return uCga;
	}

	public void setuCga(String uCga) {
		this.uCga = uCga;
	}

	public Integer getGrupoComissao() {
		return grupoComissao;
	}

	public void setGrupoComissao(Integer grupoComissao) {
		this.grupoComissao = grupoComissao;
	}

	public String getuAgrupador() {
		return uAgrupador;
	}

	public void setuAgrupador(String uAgrupador) {
		this.uAgrupador = uAgrupador;
	}

}
