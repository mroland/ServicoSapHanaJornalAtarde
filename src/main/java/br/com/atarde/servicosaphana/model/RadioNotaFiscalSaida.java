package br.com.atarde.servicosaphana.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.Status;

@SuppressWarnings("serial")
public class RadioNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable {

	private ParceiroNegocio anunciante;
	private BigDecimal uComissaoAgencia;
	private String uNumeroPI;
	private Date uDataContrato;
	private String uPeriodoVeiculacao;
	private List<RadioNotaFiscalSaidaLinha> linhas;
	private String uAutorizacaoPublicidade;
	private String uTipoTransacao;
	private String uEntregaVendedor;
	private String uPostoId;
	private Date uDataPublicacaoInicial;
	private String uObservacao;
	private Integer uPermuta;

	public RadioNotaFiscalSaida() {
	}

	public RadioNotaFiscalSaida(Status status) {

		this.setStatus(status);

	}

	public RadioNotaFiscalSaida(Empresa empresa) {
		this.setEmpresa(empresa);
	}

	public RadioNotaFiscalSaida(String atributo, Long valor) {

		if ("interfaceId".equals(atributo)) {

			this.setInterfaceId(valor);

		}
	}

	public Integer getUPermuta() {
		return uPermuta;
	}

	public void setUPermuta(Integer uPermuta) {
		this.uPermuta = uPermuta;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public BigDecimal getUComissaoAgencia() {
		return uComissaoAgencia;
	}

	public void setUComissaoAgencia(BigDecimal uComissaoAgencia) {
		this.uComissaoAgencia = uComissaoAgencia;
	}

	public ParceiroNegocio getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(ParceiroNegocio anunciante) {
		this.anunciante = anunciante;
	}

	public String getUNumeroPI() {
		return uNumeroPI;
	}

	public void setUNumeroPI(String uNumeroPI) {
		this.uNumeroPI = uNumeroPI;
	}

	public Date getUDataContrato() {
		return uDataContrato;
	}

	public void setUDataContrato(Date uDataContrato) {
		this.uDataContrato = uDataContrato;
	}

	public String getUPeriodoVeiculacao() {
		return uPeriodoVeiculacao;
	}

	public void setUPeriodoVeiculacao(String uPeriodoVeiculacao) {
		this.uPeriodoVeiculacao = uPeriodoVeiculacao;
	}

	public List<RadioNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<RadioNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public String getUAutorizacaoPublicidade() {
		return uAutorizacaoPublicidade;
	}

	public void setUAutorizacaoPublicidade(String uAutorizacaoPublicidade) {
		this.uAutorizacaoPublicidade = uAutorizacaoPublicidade;
	}

	public String getUTipoTransacao() {
		return uTipoTransacao;
	}

	public void setUTipoTransacao(String uTipoTransacao) {
		this.uTipoTransacao = uTipoTransacao;
	}

	public String getUEntregaVendedor() {
		return uEntregaVendedor;
	}

	public void setUEntregaVendedor(String uEntregaVendedor) {
		this.uEntregaVendedor = uEntregaVendedor;
	}

	public String getUPostoId() {
		return uPostoId;
	}

	public void setUPostoId(String uPostoId) {
		this.uPostoId = uPostoId;
	}

	public Date getUDataPublicacaoInicial() {
		return uDataPublicacaoInicial;
	}

	public void setUDataPublicacaoInicial(Date uDataPublicacaoInicial) {
		this.uDataPublicacaoInicial = uDataPublicacaoInicial;
	}

}
