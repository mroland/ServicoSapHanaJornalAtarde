package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class ClassificadosExecucaoNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentLines")
	private List<ClassificadosExecucaoNotaFiscalSaidaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_ValorBruto")
	private Double uValorBruto;

	public List<ClassificadosExecucaoNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ClassificadosExecucaoNotaFiscalSaidaLinhaModel> linhas) {
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

}
