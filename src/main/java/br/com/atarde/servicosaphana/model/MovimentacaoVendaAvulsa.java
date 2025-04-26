package br.com.atarde.servicosaphana.model;

import br.com.atarde.servicosaphana.sap.model.DocumentoAB;
import br.com.atarde.servicosaphana.sap.model.Item;

@SuppressWarnings("serial")
public class MovimentacaoVendaAvulsa extends DocumentoAB {

	private Long interfaceId;
	private String tipoMovimentacao;
	private Item item;
	private Double quantidade;
	private String arquivoRemessa;

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

}
