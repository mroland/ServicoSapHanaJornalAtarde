package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VendaVulsaNotaFiscalSaidaMovimentacao extends MovimentacaoVendaAvulsa implements Serializable {

	private VendaAvulsaNotaFiscalSaida notaFiscalSaidaReferenciada;

	private Long sapNotaFiscalId;

	public VendaVulsaNotaFiscalSaidaMovimentacao() {

	}

	public VendaVulsaNotaFiscalSaidaMovimentacao(VendaAvulsaNotaFiscalSaida notaFiscalSaidaReferenciada) {

		this.notaFiscalSaidaReferenciada = notaFiscalSaidaReferenciada;
	}

	public VendaAvulsaNotaFiscalSaida getNotaFiscalSaidaReferenciada() {
		return notaFiscalSaidaReferenciada;
	}

	public void setNotaFiscalSaidaReferenciada(VendaAvulsaNotaFiscalSaida notaFiscalSaidaReferenciada) {
		this.notaFiscalSaidaReferenciada = notaFiscalSaidaReferenciada;
	}

	public Long getSapNotaFiscalId() {
		return sapNotaFiscalId;
	}

	public void setSapNotaFiscalId(Long sapNotaFiscalId) {
		this.sapNotaFiscalId = sapNotaFiscalId;
	}

}
