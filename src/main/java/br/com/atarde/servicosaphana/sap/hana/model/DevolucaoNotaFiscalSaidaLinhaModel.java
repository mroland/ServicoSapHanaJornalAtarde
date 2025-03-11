package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class DevolucaoNotaFiscalSaidaLinhaModel implements Serializable {

	@SerializedName("LineNum")
	private Integer numeroLinha;

	@SerializedName("ItemCode")
	private String itemId;

	@SerializedName("ItemDetails")
	private String itemDescricao;

	@SerializedName("Quantity")
	private Double quantidade;

	@SerializedName("LineTotal")
	private Double valor;

	@SerializedName("Usage")
	private String utilizacaoId;

	@SerializedName("TaxCode")
	private String codigoImpostoId;

	@SerializedName("CFOPCode")
	private String cfop;

	@SerializedName("CSTCode")
	private String cstIcms;

	@SerializedName("CSTforCOFINS")
	private String cstCofins;

	@SerializedName("CSTforIPI")
	private String cstIpi;

	@SerializedName("CSTforPIS")
	private String cstPis;

	@SerializedName("AccountCode")
	private String contaContabilId;

	@SerializedName("TaxOnly")
	private String flagImposto;

	@SerializedName("WarehouseCode")
	private String depositoId;

	@SerializedName("CostingCode")
	private String unidadeNegocioId;

	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDescricao() {
		return itemDescricao;
	}

	public void setItemDescricao(String itemDescricao) {
		this.itemDescricao = itemDescricao;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getUtilizacaoId() {
		return utilizacaoId;
	}

	public void setUtilizacaoId(String utilizacaoId) {
		this.utilizacaoId = utilizacaoId;
	}

	public String getCodigoImpostoId() {
		return codigoImpostoId;
	}

	public void setCodigoImpostoId(String codigoImpostoId) {
		this.codigoImpostoId = codigoImpostoId;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getCstIcms() {
		return cstIcms;
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}

	public String getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(String cstCofins) {
		this.cstCofins = cstCofins;
	}

	public String getCstIpi() {
		return cstIpi;
	}

	public void setCstIpi(String cstIpi) {
		this.cstIpi = cstIpi;
	}

	public String getCstPis() {
		return cstPis;
	}

	public void setCstPis(String cstPis) {
		this.cstPis = cstPis;
	}

	public String getContaContabilId() {
		return contaContabilId;
	}

	public void setContaContabilId(String contaContabilId) {
		this.contaContabilId = contaContabilId;
	}

	public String getFlagImposto() {
		return flagImposto;
	}

	public void setFlagImposto(String flagImposto) {
		this.flagImposto = flagImposto;
	}

	public String getDepositoId() {
		return depositoId;
	}

	public void setDepositoId(String depositoId) {
		this.depositoId = depositoId;
	}

	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

}
