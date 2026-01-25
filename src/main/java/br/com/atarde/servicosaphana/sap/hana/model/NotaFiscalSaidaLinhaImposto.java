package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinhaImposto implements Serializable {

	@SerializedName("WTCode")
	private String impostoId;

	public String getImpostoId() {
		return impostoId;
	}

	public void setImpostoId(String impostoId) {
		this.impostoId = impostoId;
	}

}
