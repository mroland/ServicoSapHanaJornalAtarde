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
public class FotografiaNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable {

    private FotografiaNotaFiscalSaida notaFiscalSaida;
	private PedidoVendaLinha pedidoVendaLinha;        

    public FotografiaNotaFiscalSaidaLinha() {
    }

    public FotografiaNotaFiscalSaidaLinha(FotografiaNotaFiscalSaida nota) {
        this.notaFiscalSaida = nota;
    }

    public FotografiaNotaFiscalSaidaLinha(Long id) {
        this.setId(id);
    }

	public FotografiaNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(FotografiaNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}


}
