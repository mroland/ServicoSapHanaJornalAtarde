/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaParcela;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlSeeAlso({ParcelaNotaFiscalSaida.class, AssinaturaNotaFiscalSaidaParcela.class})
public abstract class ParcelaAB implements Serializable {

    private Long id;
    private Date dataVencimentoInicial;
    private Date dataVencimentoFinal;
    private Date dataVencimento;
    private BigDecimal valor;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private BigDecimal valorAberto;    
    private Boolean flagPago;
    private Boolean flagBoleto;
    private Integer numero;
    private Status status;
	private Boolean flagEmail;
	private Empresa empresa;
	
    private Date dataPagamento;
    private BigDecimal valorPago;
	private Boleto boleto;		

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getFlagBoleto() {
        return flagBoleto;
    }

    public void setFlagBoleto(Boolean flagBoleto) {
        this.flagBoleto = flagBoleto;
    }

    public Boolean getFlagPago() {
        return flagPago;
    }

    public void setFlagPago(Boolean flagPago) {
        this.flagPago = flagPago;
    }

    public Date getDataVencimentoFinal() {
        return dataVencimentoFinal;
    }

    public void setDataVencimentoFinal(Date dataVencimentoFinal) {
        this.dataVencimentoFinal = dataVencimentoFinal;
    }

    public Date getDataVencimentoInicial() {
        return dataVencimentoInicial;
    }

    public void setDataVencimentoInicial(Date dataVencimentoInicial) {
        this.dataVencimentoInicial = dataVencimentoInicial;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Boolean getFlagEmail() {
		return flagEmail;
	}

	public void setFlagEmail(Boolean flagEmail) {
		this.flagEmail = flagEmail;
	}

	public String getIdFormatado() {
		return id.toString() + numero.toString();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getValorAberto() {
		return valorAberto;
	}

	public void setValorAberto(BigDecimal valorAberto) {
		this.valorAberto = valorAberto;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
	
	
    
}
