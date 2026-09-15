/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinhaAB;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ClassificadosExecucaoPedidoVendaLinha extends PedidoVendaLinhaAB implements Serializable {

	private String uCmXCol;

	private BigDecimal uArea;

	private Integer uQuantidadeInsercoes;

	private BigDecimal uTotalCmXCol;

	private BigDecimal uValorUnitario;

	private ClassificadosExecucaoPedidoVenda pedidoVenda;

	public ClassificadosExecucaoPedidoVendaLinha() {

	}

	public ClassificadosExecucaoPedidoVendaLinha(ClassificadosExecucaoPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public ClassificadosExecucaoPedidoVendaLinha(Long id) {
		this.setId(id);
	}

	public ClassificadosExecucaoPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(ClassificadosExecucaoPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public String getUCmXCol() {
		return uCmXCol;
	}

	public void setUCmXCol(String uCmXCol) {
		this.uCmXCol = uCmXCol;
	}

	public BigDecimal getUArea() {
		return uArea;
	}

	public void setUArea(BigDecimal uArea) {
		this.uArea = uArea;
	}

	public Integer getUQuantidadeInsercoes() {
		return uQuantidadeInsercoes;
	}

	public void setUQuantidadeInsercoes(Integer uQuantidadeInsercoes) {
		this.uQuantidadeInsercoes = uQuantidadeInsercoes;
	}

	public BigDecimal getUTotalCmXCol() {
		return uTotalCmXCol;
	}

	public void setUTotalCmXCol(BigDecimal uTotalCmXCol) {
		this.uTotalCmXCol = uTotalCmXCol;
	}

	public BigDecimal getUValorUnitario() {
		return uValorUnitario;
	}

	public void setUValorUnitario(BigDecimal uValorUnitario) {
		this.uValorUnitario = uValorUnitario;
	}

}
