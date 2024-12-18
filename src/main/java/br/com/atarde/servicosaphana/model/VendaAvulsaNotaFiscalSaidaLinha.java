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
public class VendaAvulsaNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable {

    private VendaAvulsaNotaFiscalSaida notaFiscalSaida;
	private PedidoVendaLinha pedidoVendaLinha;        

    public VendaAvulsaNotaFiscalSaidaLinha() {
    }

    public VendaAvulsaNotaFiscalSaidaLinha(VendaAvulsaNotaFiscalSaida nota) {
        this.notaFiscalSaida = nota;
    }

    public VendaAvulsaNotaFiscalSaidaLinha(Long id) {
        this.setId(id);
    }

	public VendaAvulsaNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(VendaAvulsaNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}
    
    

}
