package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class VendaAvulsaNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentInstallments")
	private List<VendaAvulsaParcelaNotaFiscalSaidaModel> parcelas;

	@SerializedName("DocumentLines")
	private List<VendaAvulsaNotaFiscalSaidaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_Observacao")
	private String uObservacao;

	@SerializedName("U_ATRD_Banca")
	private String uBanca;

	@SerializedName("U_ATRD_Lote")
	private String uLote;

	@SerializedName("U_ATRD_TipoBanca")
	private String uTipoBanca;

	@SerializedName("U_ATRD_TipoFaturamento")
	private String uTipoFaturamento;

	public List<VendaAvulsaParcelaNotaFiscalSaidaModel> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<VendaAvulsaParcelaNotaFiscalSaidaModel> parcelas) {
		this.parcelas = parcelas;
	}

	public List<VendaAvulsaNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<VendaAvulsaNotaFiscalSaidaLinhaModel> linhas) {
		this.linhas = linhas;
	}

	public String getuEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setuEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public String getUBanca() {
		return uBanca;
	}

	public void setUBanca(String uBanca) {
		this.uBanca = uBanca;
	}

	public String getULote() {
		return uLote;
	}

	public void setULote(String uLote) {
		this.uLote = uLote;
	}

	public String getuTipoBanca() {
		return uTipoBanca;
	}

	public void setUTipoBanca(String uTipoBanca) {
		this.uTipoBanca = uTipoBanca;
	}

	public String getUTipoFaturamento() {
		return uTipoFaturamento;
	}

	public void setUTipoFaturamento(String uTipoFaturamento) {
		this.uTipoFaturamento = uTipoFaturamento;
	}

}
