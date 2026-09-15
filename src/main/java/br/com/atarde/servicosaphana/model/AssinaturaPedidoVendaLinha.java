/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinhaAB;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class AssinaturaPedidoVendaLinha extends PedidoVendaLinhaAB implements Serializable {

	private AssinaturaPedidoVenda pedidoVenda;

	public AssinaturaPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(AssinaturaPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
