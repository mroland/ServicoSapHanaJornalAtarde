/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class FotografiaNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable{

    private String uLimitacaoUso;
    
    private String uObservacao;   
    
    private List<FotografiaNotaFiscalSaidaLinha> linhas;

    public FotografiaNotaFiscalSaida(){

    }
    
    public FotografiaNotaFiscalSaida(Status status) {
        
        this.setStatus(status);
        
    }     

    public FotografiaNotaFiscalSaida(Long id) {
        this.setId(id);
    }

	public FotografiaNotaFiscalSaida(String atributo, Long interfaceId) {

		if("interfaceId".equals(atributo)){
			
			this.setInterfaceId(interfaceId);
			
		}
		
	}

	public FotografiaNotaFiscalSaida(Empresa empresa) {
		this.setEmpresa(empresa);
	}

	public FotografiaNotaFiscalSaida(Empresa empresa, Status status) {

		this.setEmpresa(empresa);
		
		this.setStatus(status);
		
	}

	public String getULimitacaoUso() {
		return uLimitacaoUso;
	}

	public void setULimitacaoUso(String uLimitacaoUso) {
		this.uLimitacaoUso = uLimitacaoUso;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public List<FotografiaNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<FotografiaNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

}
