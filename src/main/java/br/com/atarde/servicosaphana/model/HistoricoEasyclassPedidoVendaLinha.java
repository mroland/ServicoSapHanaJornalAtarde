/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class HistoricoEasyclassPedidoVendaLinha extends EasyclassPedidoVendaLinha implements Serializable {

	public HistoricoEasyclassPedidoVendaLinha(EasyclassPedidoVenda nota) {
		this.setPedidoVenda(getPedidoVenda());
	}

	public HistoricoEasyclassPedidoVendaLinha() {

	}

}
