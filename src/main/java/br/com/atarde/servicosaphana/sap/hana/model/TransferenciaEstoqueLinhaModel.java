package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class TransferenciaEstoqueLinhaModel implements Serializable {

	@SerializedName("LineNum")
	private Integer numeroLinha;

	@SerializedName("ItemCode")
	private String itemId;

	@SerializedName("Quantity")
	private Double quantidade;

	@SerializedName("FromWarehouseCode")
	private String depositoOrigemId;

	@SerializedName("WarehouseCode")
	private String depositoDestinoId;

	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getDepositoOrigemId() {
		return depositoOrigemId;
	}

	public void setDepositoOrigemId(String depositoOrigemId) {
		this.depositoOrigemId = depositoOrigemId;
	}

	public String getDepositoDestinoId() {
		return depositoDestinoId;
	}

	public void setDepositoDestinoId(String depositoDestinoId) {
		this.depositoDestinoId = depositoDestinoId;
	}

}
