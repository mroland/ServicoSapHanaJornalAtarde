/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParcelaPedidoVenda extends ParcelaAB implements Serializable {

	private Long interfaceId;
	private PedidoVenda pedidoVenda;
	private Integer diasAtraso;

	public ParcelaPedidoVenda() {

	}

	public ParcelaPedidoVenda(Long id, Integer numero) {

		this.setId(id);

		this.setNumero(numero);
	}

	public ParcelaPedidoVenda(Long id, Integer numero, Empresa empresa) {

		this.setId(id);

		this.setNumero(numero);

		this.setEmpresa(empresa);

	}

	public ParcelaPedidoVenda(Long id, Integer numero, Empresa empresa, BigDecimal valorAplicado) {

		this.setId(id);

		this.setNumero(numero);

		this.setEmpresa(empresa);

		this.setValorAberto(valorAplicado);

	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

}
