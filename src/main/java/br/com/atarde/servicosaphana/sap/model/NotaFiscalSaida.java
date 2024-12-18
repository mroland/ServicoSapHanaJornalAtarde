package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class NotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable {
	
    private Long serialInicial;
    private Long serialFinal;
    private Long idInicial;
    private Long idFinal;    
    private Date dataLancamentoInicial;
    private Date dataLancamentoFinal;
    private ParceiroNegocio anunciante;
    private Boolean flagBoleto;
    private String autorizacaoPublicidade;
    private String numeroPI;
    private NotaFiscalSaidaEletronica notaFiscalSaidaEletronica;
    private String arquivoUpload;
    private String uTipoTransacao;

    public NotaFiscalSaida() {
    }

    public NotaFiscalSaida(Long id) {
        this.setId(id);
    }

	public Long getSerialInicial() {
		return serialInicial;
	}

	public void setSerialInicial(Long serialInicial) {
		this.serialInicial = serialInicial;
	}

	public Long getSerialFinal() {
		return serialFinal;
	}

	public void setSerialFinal(Long serialFinal) {
		this.serialFinal = serialFinal;
	}

	public Long getIdInicial() {
		return idInicial;
	}

	public void setIdInicial(Long idInicial) {
		this.idInicial = idInicial;
	}

	public Long getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(Long idFinal) {
		this.idFinal = idFinal;
	}

	public ParceiroNegocio getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(ParceiroNegocio anunciante) {
		this.anunciante = anunciante;
	}

	public Boolean getFlagBoleto() {
		return flagBoleto;
	}

	public void setFlagBoleto(Boolean flagBoleto) {
		this.flagBoleto = flagBoleto;
	}

	public String getAutorizacaoPublicidade() {
		return autorizacaoPublicidade;
	}

	public void setAutorizacaoPublicidade(String autorizacaoPublicidade) {
		this.autorizacaoPublicidade = autorizacaoPublicidade;
	}

	public String getNumeroPI() {
		return numeroPI;
	}

	public void setNumeroPI(String numeroPI) {
		this.numeroPI = numeroPI;
	}

	public NotaFiscalSaidaEletronica getNotaFiscalSaidaEletronica() {
		return notaFiscalSaidaEletronica;
	}

	public void setNotaFiscalSaidaEletronica(NotaFiscalSaidaEletronica notaFiscalSaidaEletronica) {
		this.notaFiscalSaidaEletronica = notaFiscalSaidaEletronica;
	}

	public String getArquivoUpload() {
		return arquivoUpload;
	}

	public void setArquivoUpload(String arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public String getUTipoTransacao() {
		return uTipoTransacao;
	}

	public void setUTipoTransacao(String uTipoTransacao) {
		this.uTipoTransacao = uTipoTransacao;
	}

	public Date getDataLancamentoInicial() {
		return dataLancamentoInicial;
	}

	public void setDataLancamentoInicial(Date dataLancamentoInicial) {
		this.dataLancamentoInicial = dataLancamentoInicial;
	}

	public Date getDataLancamentoFinal() {
		return dataLancamentoFinal;
	}

	public void setDataLancamentoFinal(Date dataLancamentoFinal) {
		this.dataLancamentoFinal = dataLancamentoFinal;
	}

}
