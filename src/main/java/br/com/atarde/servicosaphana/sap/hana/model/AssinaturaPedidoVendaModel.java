package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class AssinaturaPedidoVendaModel extends PedidoVendaModel {

	@SerializedName("DocumentInstallments")
	private List<AssinaturaParcelaPedidoVendaModel> parcelas;

	@SerializedName("DocumentLines")
	private List<AssinaturaPedidoVendaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_Observacao")
	private String uObservacao;

	@SerializedName("U_ATRD_Termo")
	private String uTermo;

	public List<AssinaturaParcelaPedidoVendaModel> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<AssinaturaParcelaPedidoVendaModel> parcelas) {
		this.parcelas = parcelas;
	}

	public List<AssinaturaPedidoVendaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<AssinaturaPedidoVendaLinhaModel> linhas) {
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
