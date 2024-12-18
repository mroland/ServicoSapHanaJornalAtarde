/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinha;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class AssinaturaNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable {
	
    private AssinaturaNotaFiscalSaida notaFiscalSaida;
	private PedidoVendaLinha pedidoVendaLinha;    
    
	public AssinaturaNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}
	public void setNotaFiscalSaida(AssinaturaNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}
	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}
	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}


}
