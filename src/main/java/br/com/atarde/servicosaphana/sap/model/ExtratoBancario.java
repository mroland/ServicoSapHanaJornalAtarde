package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ExtratoBancario implements Serializable{

	//Tabela OBNK
	
	private Long interfaceId;
	private String conta;
	private ContaContabil contaContabil;
	private BigDecimal valorCredito;
	private BigDecimal valorDebito;
	private CodigoInterno codigoInterno;
	private String detalhe;
	private Empresa empresa;
	private String mensagemErro;
	private Date dataVencimento;
	private String numeroDocumento;
	private Status status;
	private Date dataImportacao;
	private Date dataExportacao;
	private Date dataAtualizacao;
	private Boolean flagDia;		
	
	public ExtratoBancario(){
		
	}

	public ExtratoBancario(Empresa empresa) {
		this.empresa = empresa;
	}


	public ExtratoBancario(Status status) {
		this.status = status;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public ContaContabil getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}
	public BigDecimal getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	public CodigoInterno getCodigoInterno() {
		return codigoInterno;
	}
	public void setCodigoInterno(CodigoInterno codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Date getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Date dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Boolean getFlagDia() {
		return flagDia;
	}

	public void setFlagDia(Boolean flagDia) {
		this.flagDia = flagDia;
	}
	
	
}
