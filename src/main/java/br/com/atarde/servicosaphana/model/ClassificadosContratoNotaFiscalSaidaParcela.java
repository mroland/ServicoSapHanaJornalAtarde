package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class ClassificadosContratoNotaFiscalSaidaParcela extends ParcelaAB {

	private Long interfaceId;
	private ClassificadosContratoNotaFiscalSaida notaFiscalSaida;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public ClassificadosContratoNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(ClassificadosContratoNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

}
