package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class FilialParceiroNegocioModel implements Serializable {

	@SerializedName("BPLID")
	private Integer id;

	@SerializedName("BPCode")
	private String parceiroId;

	@SerializedName("DisabledForBP")
	private String desabilitado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParceiroId() {
		return parceiroId;
	}

	public void setParceiroId(String parceiroId) {
		this.parceiroId = parceiroId;
	}

	public String getDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(String desabilitado) {
		this.desabilitado = desabilitado;
	}

}
