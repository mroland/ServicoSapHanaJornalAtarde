/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParcelaNotaFiscalSaida extends ParcelaAB implements Serializable {

    private Long interfaceId;
    private ContasReceberLinha contasReceberLinha;
    private List<ContasReceberLinha> contasReceberLinhas;
    private NotaFiscalSaida notaFiscalSaida;
	private Integer diasAtraso;

	public ParcelaNotaFiscalSaida(){
		
	}
	
    public ParcelaNotaFiscalSaida(Long id, Integer numero) {
    	
    	this.setId(id);

    	this.setNumero(numero);
	}

	public ParcelaNotaFiscalSaida(Long id, Integer numero, Empresa empresa) {

    	this.setId(id);

    	this.setNumero(numero);
    	
    	this.setEmpresa(empresa);
    	
	}

	public ParcelaNotaFiscalSaida(Long id, Integer numero, Empresa empresa, BigDecimal valorAplicado) {

    	this.setId(id);

    	this.setNumero(numero);
    	
    	this.setEmpresa(empresa);
    	
    	this.setValorAberto(valorAplicado);
    	
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public ContasReceberLinha getContasReceberLinha() {
		return contasReceberLinha;
	}

	public void setContasReceberLinha(ContasReceberLinha contasReceberLinha) {
		this.contasReceberLinha = contasReceberLinha;
	}

	public List<ContasReceberLinha> getContasReceberLinhas() {
		return contasReceberLinhas;
	}

	public void setContasReceberLinhas(List<ContasReceberLinha> contasReceberLinhas) {
		this.contasReceberLinhas = contasReceberLinhas;
	}

	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	
    
}
