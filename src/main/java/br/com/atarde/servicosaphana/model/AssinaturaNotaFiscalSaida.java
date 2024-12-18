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
import br.com.atarde.servicosaphana.sap.model.Usuario;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class AssinaturaNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable{
	
    private String uObservacao;

    private List<AssinaturaNotaFiscalSaidaLinha> linhas; 

	private Usuario usuario;

	private String uTermo;
	
    public AssinaturaNotaFiscalSaida(){

    }

    public AssinaturaNotaFiscalSaida(Long id) {
        this.setInterfaceId(id);
    }
    
    public AssinaturaNotaFiscalSaida(Status status) {
        this.setStatus(status);
    }    

    public AssinaturaNotaFiscalSaida(Empresa empresa) {
    	
		this.setEmpresa(empresa);
		
	}

	public AssinaturaNotaFiscalSaida(String atributo, Long interfaceId) {

		if("interfaceId".equals(atributo)){
			
			this.setInterfaceId(interfaceId);
			
		}
		
	}

	public String getUObservacao() {
        return uObservacao;
    }

    public void setUObservacao(String uObservacao) {
        this.uObservacao = uObservacao;
    }

	public List<AssinaturaNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<AssinaturaNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUTermo() {
		return uTermo;
	}

	public void setUTermo(String uTermo) {
		this.uTermo = uTermo;
	}
   
}
