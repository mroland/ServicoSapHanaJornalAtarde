package br.com.atarde.servicosaphana.business;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class TabelaUsuarioImpostoTaxa implements Serializable {

	private String impostoId;

	private BigDecimal percentual;

	public String getImpostoId() {
		return impostoId;
	}

	public void setImpostoId(String impostoId) {
		this.impostoId = impostoId;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

}
