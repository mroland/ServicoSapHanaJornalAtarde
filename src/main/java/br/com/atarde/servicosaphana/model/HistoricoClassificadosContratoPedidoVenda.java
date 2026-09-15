package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HistoricoClassificadosContratoPedidoVenda extends ClassificadosContratoPedidoVenda implements Serializable {

	private Long interfaceOriginalId;

	public Long getInterfaceOriginalId() {
		return interfaceOriginalId;
	}

	public void setInterfaceOriginalId(Long interfaceOriginalId) {
		this.interfaceOriginalId = interfaceOriginalId;
	}

}
