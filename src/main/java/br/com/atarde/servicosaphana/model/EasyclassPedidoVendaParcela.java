package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class EasyclassPedidoVendaParcela extends ParcelaAB {

	private Long interfaceId;
	private EasyclassPedidoVenda pedidoVenda;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public EasyclassPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(EasyclassPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
