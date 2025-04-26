package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class TabelaUsuarioMovimentacaoModel implements Serializable {

	@SerializedName("Code")
	private Long id;

	@SerializedName("Name")
	private Long nome;

	@SerializedName("U_TipMov")
	private String tipoMovimentacao;

	@SerializedName("U_RefVda")
	private String idExterno;

	@SerializedName("U_NumDoc")
	private Long docentry;

	@SerializedName("U_QtdVda")
	private Double quantidade;

	@SerializedName("U_ItemCode")
	private String itemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNome() {
		return nome;
	}

	public void setNome(Long nome) {
		this.nome = nome;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public Long getDocentry() {
		return docentry;
	}

	public void setDocentry(Long docentry) {
		this.docentry = docentry;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	

}
