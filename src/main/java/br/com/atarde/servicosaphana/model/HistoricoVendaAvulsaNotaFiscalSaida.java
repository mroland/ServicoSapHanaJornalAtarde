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
public class HistoricoVendaAvulsaNotaFiscalSaida extends VendaAvulsaNotaFiscalSaida implements Serializable {

	private Long interfaceOriginalId;

	public Long getInterfaceOriginalId() {
		return interfaceOriginalId;
	}

	public void setInterfaceOriginalId(Long interfaceOriginalId) {
		this.interfaceOriginalId = interfaceOriginalId;
	}

}
