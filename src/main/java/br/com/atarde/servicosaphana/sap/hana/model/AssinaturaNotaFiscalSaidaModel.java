package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class AssinaturaNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentInstallments")
	private List<AssinaturaParcelaNotaFiscalSaidaModel> parcelas;

	@SerializedName("DocumentLines")
	private List<AssinaturaNotaFiscalSaidaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_Observacao")
	private String uObservacao;

	@SerializedName("U_ATRD_Termo")
	private String uTermo;

	public List<AssinaturaParcelaNotaFiscalSaidaModel> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<AssinaturaParcelaNotaFiscalSaidaModel> parcelas) {
		this.parcelas = parcelas;
	}

	public List<AssinaturaNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<AssinaturaNotaFiscalSaidaLinhaModel> linhas) {
		this.linhas = linhas;
	}

	public String getUEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setUEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public String getUTermo() {
		return uTermo;
	}

	public void setUTermo(String uTermo) {
		this.uTermo = uTermo;
	}

}
