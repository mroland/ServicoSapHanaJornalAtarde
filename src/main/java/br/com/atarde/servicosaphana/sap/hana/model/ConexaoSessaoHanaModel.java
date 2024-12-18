package br.com.atarde.servicosaphana.sap.hana.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.Utilitarios;

@SuppressWarnings("serial")
public class ConexaoSessaoHanaModel implements Serializable {

	private Long id;
	private Empresa empresaModel;

	@SerializedName("SessionId")
	private String sessaoId;

	@SerializedName("SessionTimeout")
	private String sessaoTimeout;

	@SerializedName("Version")
	private String versao;

	private Date dataInicial;
	private Date dataExpiracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresaModel() {
		return empresaModel;
	}

	public void setEmpresaModel(Empresa empresaModel) {
		this.empresaModel = empresaModel;
	}

	public String getSessaoId() {
		return sessaoId;
	}

	public void setSessaoId(String sessaoId) {
		this.sessaoId = sessaoId;
	}

	public String getSessaoTimeout() {
		return sessaoTimeout;
	}

	public void setSessaoTimeout(String sessaoTimeout) {
		this.sessaoTimeout = sessaoTimeout;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public boolean isExpirou(Date dataReferencia) {

		return !Utilitarios.isBetweenDateTime(dataReferencia, this.dataInicial, this.dataExpiracao) ? true : false;

	}

}
