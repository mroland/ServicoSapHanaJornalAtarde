package br.com.atarde.servicosaphana.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class RadioNotaFiscalSaidaLinhaImposto implements Serializable {

	private Long id;

	private Long interfaceId;

	private RadioNotaFiscalSaidaLinha linha;

	private String impostoId;

	private BigDecimal valor;

	private BigDecimal percentual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RadioNotaFiscalSaidaLinha getLinha() {
		return linha;
	}

	public void setLinha(RadioNotaFiscalSaidaLinha linha) {
		this.linha = linha;
	}

	public String getImpostoId() {
		return impostoId;
	}

	public void setImpostoId(String impostoId) {
		this.impostoId = impostoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

}
