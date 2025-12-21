package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class RadioNotaFiscalSaidaParcela extends ParcelaAB {

	private Long interfaceId;
	private RadioNotaFiscalSaida notaFiscalSaida;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public RadioNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(RadioNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

}
