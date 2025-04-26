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

}
