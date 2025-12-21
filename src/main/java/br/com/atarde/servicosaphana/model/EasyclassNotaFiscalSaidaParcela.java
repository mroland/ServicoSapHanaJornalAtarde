package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class EasyclassNotaFiscalSaidaParcela extends ParcelaAB {

	private Long interfaceId;
	private EasyclassNotaFiscalSaida notaFiscalSaida;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public EasyclassNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(EasyclassNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

}
