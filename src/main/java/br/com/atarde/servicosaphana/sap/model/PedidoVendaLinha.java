package br.com.atarde.servicosaphana.sap.model;

@SuppressWarnings("serial")
public class PedidoVendaLinha extends PedidoVendaLinhaAB {

	private PedidoVenda pedidoVenda;

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
