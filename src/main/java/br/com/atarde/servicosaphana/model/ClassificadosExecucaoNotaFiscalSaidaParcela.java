package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class ClassificadosExecucaoNotaFiscalSaidaParcela extends ParcelaAB {

	private Long interfaceId;
	private ClassificadosExecucaoNotaFiscalSaida notaFiscalSaida;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public ClassificadosExecucaoNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(ClassificadosExecucaoNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

}
