package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class NotaFiscalSaidaModel implements Serializable {

	@SerializedName("DocEntry")
	private Long id;

	@SerializedName("BPL_IDAssignedToInvoice")
	private Integer filialId;

	@SerializedName("DocType")
	private String tipoDocumento = "dDocument_Items";

	@SerializedName("DocObjectCode")
	private String tipoObjeto = "oInvoices"; // "oOrders";

	@SerializedName("DocDate")
	private String dataLancamento;

	@SerializedName("DocDueDate")
	private String dataVencimento;

	@SerializedName("TaxDate")
	private String dataDocumento;

	@SerializedName("CardCode")
	private String parceiroNegocioId;

	@SerializedName("ShipToCode")
	private String enderecoEntregaId;

	@SerializedName("PayToCode")
	private String enderecoCobrancaId;

	@SerializedName("NumAtCard")
	private String idExterno;

	@SerializedName("SalesPersonCode")
	private Integer vendedorId;

	@SerializedName("SequenceCode")
	private Integer sequenciaId;;

	@SerializedName("PaymentGroupCode")
	private Integer condicaoPagamentoId;

	@SerializedName("Comments")
	private String observacao;

	@SerializedName("Incoterms")
	private String incoterms;

	@SerializedName("U_ATRD_Origem")
	private Integer uOrigem;

	@SerializedName("DocumentReferences")
	private List<DocumentoReferencia> referencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public String getParceiroNegocioId() {
		return parceiroNegocioId;
	}

	public void setParceiroNegocioId(String parceiroNegocioId) {
		this.parceiroNegocioId = parceiroNegocioId;
	}

	public String getEnderecoEntregaId() {
		return enderecoEntregaId;
	}

	public void setEnderecoEntregaId(String enderecoEntregaId) {
		this.enderecoEntregaId = enderecoEntregaId;
	}

	public String getEnderecoCobrancaId() {
		return enderecoCobrancaId;
	}

	public void setEnderecoCobrancaId(String enderecoCobrancaId) {
		this.enderecoCobrancaId = enderecoCobrancaId;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public Integer getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

	public Integer getCondicaoPagamentoId() {
		return condicaoPagamentoId;
	}

	public void setCondicaoPagamentoId(Integer condicaoPagamentoId) {
		this.condicaoPagamentoId = condicaoPagamentoId;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIncoterms() {
		return incoterms;
	}

	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}

	public Integer getUOrigem() {
		return uOrigem;
	}

	public void setUOrigem(Integer uOrigem) {
		this.uOrigem = uOrigem;
	}

	public Integer getSequenciaId() {
		return sequenciaId;
	}

	public void setSequenciaId(Integer sequenciaId) {
		this.sequenciaId = sequenciaId;
	}

	public Integer getFilialId() {
		return filialId;
	}

	public void setFilialId(Integer filialId) {
		this.filialId = filialId;
	}

	public List<DocumentoReferencia> getReferencias() {
		return referencias;
	}

	public void setReferencias(List<DocumentoReferencia> referencias) {
		this.referencias = referencias;
	}

}
