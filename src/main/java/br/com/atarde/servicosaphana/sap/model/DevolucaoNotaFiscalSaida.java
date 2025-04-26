/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class DevolucaoNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable {

	private Boolean flagConsignado;

	private List<DevolucaoNotaFiscalSaidaLinha> linhas;

	private List<TabelaUsuarioMovimentacao> movimentacoes;

	public DevolucaoNotaFiscalSaida() {

	}

	public DevolucaoNotaFiscalSaida(Status status) {

		this.setStatus(status);

	}

	public DevolucaoNotaFiscalSaida(Long id) {
		this.setId(id);
	}

	public DevolucaoNotaFiscalSaida(String atributo, Long interfaceId) {

		if ("interfaceId".equals(atributo)) {

			this.setInterfaceId(interfaceId);

		}

	}

	public DevolucaoNotaFiscalSaida(Empresa empresa) {
		this.setEmpresa(empresa);
	}

	public DevolucaoNotaFiscalSaida(Empresa empresa, Status status) {

		this.setEmpresa(empresa);

		this.setStatus(status);

	}

	public DevolucaoNotaFiscalSaida(Long interfaceId, Origem origem) {

		this.setInterfaceId(interfaceId);

		this.setOrigem(origem);
	}

	public Boolean getFlagConsignado() {
		return flagConsignado;
	}

	public void setFlagConsignado(Boolean flagConsignado) {
		this.flagConsignado = flagConsignado;
	}

	public List<DevolucaoNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<DevolucaoNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public List<TabelaUsuarioMovimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<TabelaUsuarioMovimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
