package br.com.atarde.servicosaphana.sap.model;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB{
	
	private NotaFiscalSaida notaFiscalSaida;
	
	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}
	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}
	

}
