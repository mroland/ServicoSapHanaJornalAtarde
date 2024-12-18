package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class RetornoSapErroModel implements Serializable {

	@SerializedName("error")
	private ErroSapModel erroModel;

	public ErroSapModel getErroModel() {
		return erroModel;
	}

	public void setErroModel(ErroSapModel erroModel) {
		this.erroModel = erroModel;
	}

}
