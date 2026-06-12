package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class AssinaturaPedidoVendaParcela extends ParcelaAB {

	private Long interfaceId;
	private AssinaturaPedidoVenda pedidoVenda;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public AssinaturaPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(AssinaturaPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
