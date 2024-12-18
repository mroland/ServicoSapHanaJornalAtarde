package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class ErroSapModel implements Serializable {

	@SerializedName("code")
	private Long codigo;

	@SerializedName("message")
	private MensagemErroModel mensagemErroModel;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public MensagemErroModel getMensagemErroModel() {
		return mensagemErroModel;
	}

	public void setMensagemErroModel(MensagemErroModel mensagemErroModel) {
		this.mensagemErroModel = mensagemErroModel;
	}

}
