package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HistoricoClassificadosContratoNotaFiscalSaida extends ClassificadosContratoNotaFiscalSaida implements Serializable {

	private Long interfaceOriginalId;

	public Long getInterfaceOriginalId() {
		return interfaceOriginalId;
	}

	public void setInterfaceOriginalId(Long interfaceOriginalId) {
		this.interfaceOriginalId = interfaceOriginalId;
	}

}
