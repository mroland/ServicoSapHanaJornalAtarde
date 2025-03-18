package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class TransferenciaEstoqueModel implements Serializable {

	@SerializedName("DocEntry")
	private Long id;

	@SerializedName("BPLID")
	private Integer filialId;

	@SerializedName("DocObjectCode")
	private String tipoObjeto = "67";

	@SerializedName("DocDate")
	private String dataLancamento;

	@SerializedName("DueDate")
	private String dataVencimento;

	@SerializedName("TaxDate")
	private String dataDocumento;

	@SerializedName("U_ATRD_NumAtCard")
	private String idExterno;

	@SerializedName("Comments")
	private String observacao;

	@SerializedName("JournalMemo")
	private String observacaoDiario;

	@SerializedName("U_ATRD_Origem")
	private Integer uOrigem;

	@SerializedName("FromWarehouse")
	private String depositoOrigemId;

	@SerializedName("ToWarehouse")
	private String depositoDestinoId;

	@SerializedName("StockTransferLines")
	private List<TransferenciaEstoqueLinhaModel> linhas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFilialId() {
		return filialId;
	}

	public void setFilialId(Integer filialId) {
		this.filialId = filialId;
	}

	public String getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoDiario() {
		return observacaoDiario;
	}

	public void setObservacaoDiario(String observacaoDiario) {
		this.observacaoDiario = observacaoDiario;
	}

	public Integer getUOrigem() {
		return uOrigem;
	}

	public void setUOrigem(Integer uOrigem) {
		this.uOrigem = uOrigem;
	}

	public String getDepositoOrigemId() {
		return depositoOrigemId;
	}

	public void setDepositoOrigemId(String depositoOrigemId) {
		this.depositoOrigemId = depositoOrigemId;
	}

	public String getDepositoDestinoId() {
		return depositoDestinoId;
	}

	public void setDepositoDestinoId(String depositoDestinoId) {
		this.depositoDestinoId = depositoDestinoId;
	}

	public List<TransferenciaEstoqueLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<TransferenciaEstoqueLinhaModel> linhas) {
		this.linhas = linhas;
	}

}
