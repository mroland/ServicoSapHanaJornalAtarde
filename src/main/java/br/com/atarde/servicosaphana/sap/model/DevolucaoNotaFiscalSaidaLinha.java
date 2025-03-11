/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class DevolucaoNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable {

	private DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaida;

	public DevolucaoNotaFiscalSaidaLinha() {
	}

	public DevolucaoNotaFiscalSaida getDevolucaoNotaFiscalSaida() {
		return devolucaoNotaFiscalSaida;
	}

	public void setDevolucaoNotaFiscalSaida(DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaida) {
		this.devolucaoNotaFiscalSaida = devolucaoNotaFiscalSaida;
	}

}
