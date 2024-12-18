package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("serial")
public class ContasReceberLinha implements Serializable, Cloneable{
	
	private Long id;
	private Long interfaceId;
	private ParcelaNotaFiscalSaida parcela;
	private List<ParcelaNotaFiscalSaida> parcelas;
	private ContasReceber contasReceber;
	private Empresa empresa;
	private BigDecimal valorAplicado;
	
	public ContasReceberLinha(){
		
	}
	
	public ContasReceberLinha(ParcelaNotaFiscalSaida parcela) {
		this.parcela = parcela;
	}
	public ContasReceber getContasReceber() {
		return contasReceber;
	}
	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(BigDecimal valorAplicado) {
		this.valorAplicado = valorAplicado;
	}
	
	@Override
	public ContasReceberLinha clone() throws CloneNotSupportedException  {

		ContasReceberLinha clone = (ContasReceberLinha) super.clone();
			
		return clone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParcelaNotaFiscalSaida getParcela() {
		return parcela;
	}

	public void setParcela(ParcelaNotaFiscalSaida parcela) {
		this.parcela = parcela;
	}

	public List<ParcelaNotaFiscalSaida> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaNotaFiscalSaida> parcelas) {
		this.parcelas = parcelas;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}	

	
}
