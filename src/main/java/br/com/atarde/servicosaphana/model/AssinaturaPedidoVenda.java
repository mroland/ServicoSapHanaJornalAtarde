/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.sap.model.Usuario;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class AssinaturaPedidoVenda extends PedidoVendaAB implements Serializable {

	private Boolean flagRemessa;

	private String uObservacao;

	private List<AssinaturaPedidoVendaLinha> linhas;

	private Usuario usuario;

	private String uTermo;

	public AssinaturaPedidoVenda() {

	}

	public AssinaturaPedidoVenda(Long id) {
		this.setInterfaceId(id);
	}

	public AssinaturaPedidoVenda(Status status) {
		this.setStatus(status);
	}

	public AssinaturaPedidoVenda(Empresa empresa) {

		this.setEmpresa(empresa);

	}

	public AssinaturaPedidoVenda(String atributo, Long interfaceId) {

		if ("interfaceId".equals(atributo)) {

			this.setInterfaceId(interfaceId);

		}

	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public List<AssinaturaPedidoVendaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<AssinaturaPedidoVendaLinha> linhas) {
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

	public Boolean getFlagRemessa() {
		return flagRemessa;
	}

	public void setFlagRemessa(Boolean flagRemessa) {
		this.flagRemessa = flagRemessa;
	}

}
