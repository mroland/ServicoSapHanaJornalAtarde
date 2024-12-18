package br.com.atarde.servicosaphana.util;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ConexaoModel implements Serializable {

	private Long id;
	private String CompanyDB;
	private String UserName;
	private String Password;

	private String SessionId;
	private Long SessionTimeout;

	private Date dataInicial;
	private Date dataExpiracao;

	public String getCompanyDB() {
		return CompanyDB;
	}

	public void setCompanyDB(String companyDB) {
		CompanyDB = companyDB;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public Long getSessionTimeout() {
		return SessionTimeout;
	}

	public void setSessionTimeout(Long sessionTimeout) {
		SessionTimeout = sessionTimeout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
