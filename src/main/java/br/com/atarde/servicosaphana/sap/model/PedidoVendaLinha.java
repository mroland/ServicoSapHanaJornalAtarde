package br.com.atarde.servicosaphana.sap.model;

@SuppressWarnings("serial")
public class PedidoVendaLinha extends DocumentoLinhaAB {

	private Long interfaceId;
	private PedidoVenda PedidoVenda;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public PedidoVenda getPedidoVenda() {
		return PedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		PedidoVenda = pedidoVenda;
	}


}
