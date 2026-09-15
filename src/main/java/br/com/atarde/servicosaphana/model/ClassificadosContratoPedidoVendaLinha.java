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
public class ClassificadosContratoPedidoVendaLinha extends PedidoVendaLinhaAB implements Serializable{

    private String uCmXCol;

    private BigDecimal uArea;

    private Integer uQuantidadeInsercoes;

    private BigDecimal uTotalCmXCol;

    private BigDecimal uValorUnitario;

    private ClassificadosContratoPedidoVenda pedidoVenda;
    
    public ClassificadosContratoPedidoVendaLinha(){
        
    }

    public ClassificadosContratoPedidoVendaLinha(ClassificadosContratoPedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public ClassificadosContratoPedidoVendaLinha(Long id) {
        this.setId(id);
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

	public ClassificadosContratoPedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(ClassificadosContratoPedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

}
