package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class FotografiaNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentLines")
	private List<FotografiaNotaFiscalSaidaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_ValorBruto")
	private Double uValorBruto;

	@SerializedName("U_ATRD_Observacao")
	private String uObservacao;

	@SerializedName("U_ATRD_LimitacaoUso")
	private String uLimitacaoUso;

	public List<FotografiaNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<FotografiaNotaFiscalSaidaLinhaModel> linhas) {
		this.linhas = linhas;
	}

	public String getUEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setUEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public Double getUValorBruto() {
		return uValorBruto;
	}

	public void setUValorBruto(Double uValorBruto) {
		this.uValorBruto = uValorBruto;
	}

	public String getULimitacaoUso() {
		return uLimitacaoUso;
	}

	public void setULimitacaoUso(String uLimitacaoUso) {
		this.uLimitacaoUso = uLimitacaoUso;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

}
