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
public class RadioPedidoVendaLinha extends PedidoVendaLinhaAB implements Serializable {

	private Integer uSecundagem;
	private String descricao;
	private RadioPedidoVenda pedidoVenda;

	public RadioPedidoVendaLinha(RadioPedidoVenda model) {
		this.pedidoVenda = model;
	}

	public RadioPedidoVendaLinha() {

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getUSecundagem() {
		return uSecundagem;
	}

	public void setUSecundagem(Integer uSecundagem) {
		this.uSecundagem = uSecundagem;
	}

	public RadioPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(RadioPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
