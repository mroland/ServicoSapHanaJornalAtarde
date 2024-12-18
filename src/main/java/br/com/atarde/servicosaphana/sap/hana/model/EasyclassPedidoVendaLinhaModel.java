package br.com.atarde.servicosaphana.sap.hana.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class EasyclassPedidoVendaLinhaModel extends PedidoVendaLinhaModel {

	@SerializedName("U_ATRD_Area")
	private Double uArea;

	@SerializedName("U_ATRD_CmXCol")
	private String uCmXCol;

	@SerializedName("U_ATRD_QtdInsercoes")
	private Integer uQuantidadeInsercoes;

	@SerializedName("U_ATRD_TotalCmXCol")
	private Double uTotalCmXCol;

	@SerializedName("U_ATRD_ValorUnitario")
	private Double uValorUnitario;

	public Double getUArea() {
		return uArea;
	}

	public void setUArea(Double uArea) {
		this.uArea = uArea;
	}

	public String getUCmXCol() {
		return uCmXCol;
	}

	public void setUCmXCol(String uCmXCol) {
		this.uCmXCol = uCmXCol;
	}

	public Integer getUQuantidadeInsercoes() {
		return uQuantidadeInsercoes;
	}

	public void setUQuantidadeInsercoes(Integer uQuantidadeInsercoes) {
		this.uQuantidadeInsercoes = uQuantidadeInsercoes;
	}

	public Double getUTotalCmXCol() {
		return uTotalCmXCol;
	}

	public void setUTotalCmXCol(Double uTotalCmXCol) {
		this.uTotalCmXCol = uTotalCmXCol;
	}

	public Double getUValorUnitario() {
		return uValorUnitario;
	}

	public void setUValorUnitario(Double uValorUnitario) {
		this.uValorUnitario = uValorUnitario;
	}

}
