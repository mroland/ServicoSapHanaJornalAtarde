package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class EntradaMercadoria extends DocumentoAB implements Serializable{
	
	//Tabela OIGN
	
	private Long interfaceId;	    
    private List<EntradaMercadoriaLinha> linhas;
    
    public EntradaMercadoria(){
    	
    }
      
	public EntradaMercadoria(Empresa empresa) {
		this.setEmpresa(empresa);
	}


	public EntradaMercadoria(Status status) {
		this.setStatus(status);
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public List<EntradaMercadoriaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<EntradaMercadoriaLinha> linhas) {
		this.linhas = linhas;
	}



}
