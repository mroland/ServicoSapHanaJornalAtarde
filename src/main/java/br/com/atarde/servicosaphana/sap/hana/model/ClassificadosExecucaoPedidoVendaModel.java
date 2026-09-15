package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class ClassificadosExecucaoPedidoVendaModel extends PedidoVendaModel {

	@SerializedName("DocumentLines")
	private List<ClassificadosExecucaoPedidoVendaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_ValorBruto")
	private Double uValorBruto;

	public List<ClassificadosExecucaoPedidoVendaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ClassificadosExecucaoPedidoVendaLinhaModel> linhas) {
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
