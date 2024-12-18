package br.com.atarde.servicosaphana.sap.hana.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class RadioNotaFiscalSaidaLinhaModel extends NotaFiscalSaidaLinhaModel {

	@SerializedName("U_ATRD_Secundagem")
	private String uSecundagem;

	public String getUSecundagem() {
		return uSecundagem;
	}

	public void setUSecundagem(String uSecundagem) {
		this.uSecundagem = uSecundagem;
	}

}
