package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.DocumentoAB;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Item;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.Status;

@SuppressWarnings("serial")
public class TabelaUsuarioMovimentacao extends DocumentoAB {

	private Long interfaceId;
	private String tipoMovimentacao;
	private Item item;
	private Double quantidade;
	private String arquivoRemessa;

	private NotaFiscalSaida notaFiscalSaidaReferenciada;

	private DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaidaReferenciada;

	private Long sapNotaFiscalSaidaId;

	private Long sapDevolucaoNotaFiscalSaidaId;

	public TabelaUsuarioMovimentacao() {

	}

	public TabelaUsuarioMovimentacao(NotaFiscalSaida model) {

		this.notaFiscalSaidaReferenciada = model;

	}

	public TabelaUsuarioMovimentacao(DevolucaoNotaFiscalSaida model) {

		this.devolucaoNotaFiscalSaidaReferenciada = model;
	}

	public TabelaUsuarioMovimentacao(Empresa empresa) {

		this.setEmpresa(empresa);
	}

	public TabelaUsuarioMovimentacao(Status status) {

		this.setStatus(status);

	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getArquivoRemessa() {
		return arquivoRemessa;
	}

	public void setArquivoRemessa(String arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}

	public NotaFiscalSaida getNotaFiscalSaidaReferenciada() {
		return notaFiscalSaidaReferenciada;
	}

	public void setNotaFiscalSaidaReferenciada(NotaFiscalSaida notaFiscalSaidaReferenciada) {
		this.notaFiscalSaidaReferenciada = notaFiscalSaidaReferenciada;
	}

	public DevolucaoNotaFiscalSaida getDevolucaoNotaFiscalSaidaReferenciada() {
		return devolucaoNotaFiscalSaidaReferenciada;
	}

	public void setDevolucaoNotaFiscalSaidaReferenciada(DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaidaReferenciada) {
		this.devolucaoNotaFiscalSaidaReferenciada = devolucaoNotaFiscalSaidaReferenciada;
	}

	public Long getSapNotaFiscalSaidaId() {
		return sapNotaFiscalSaidaId;
	}

	public void setSapNotaFiscalSaidaId(Long sapNotaFiscalSaidaId) {
		this.sapNotaFiscalSaidaId = sapNotaFiscalSaidaId;
	}

	public Long getSapDevolucaoNotaFiscalSaidaId() {
		return sapDevolucaoNotaFiscalSaidaId;
	}

	public void setSapDevolucaoNotaFiscalSaidaId(Long sapDevolucaoNotaFiscalSaidaId) {
		this.sapDevolucaoNotaFiscalSaidaId = sapDevolucaoNotaFiscalSaidaId;
	}

}
