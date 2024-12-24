package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class NotaFiscalSaidaLinhaAB extends DocumentoLinhaAB implements Serializable {

	private Long interfaceId;
	private Boolean flagImposto;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Boolean getFlagImposto() {
		return flagImposto;
	}

	public void setFlagImposto(Boolean flagImposto) {
		this.flagImposto = flagImposto;
	}

}
