package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class RadioNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentInstallments")
	private List<RadioParcelaNotaFiscalSaidaModel> parcelas;

	@SerializedName("DocumentLines")
	private List<RadioNotaFiscalSaidaLinhaModel> linhas;

	@SerializedName("U_ATRD_EnderecoEntrega")
	private String uEnderecoEntrega;

	@SerializedName("U_ATRD_AnuncianteId")
	private String uAnuncianteId;

	@SerializedName("U_ATRD_NumeroPI")
	private String uNumeroPI;

	@SerializedName("U_ATRD_ComissaoAgencia")
	private Double uComissaoAgencia;

	@SerializedName("U_ATRD_DtPublicacaoFinal")
	private String uDataPublicacaoFinal;

	@SerializedName("U_ATRD_DiasPublicacao")
	private String uDiasPublicacao;

	@SerializedName("U_ATRD_ValorBruto")
	private Double uValorBruto;

	@SerializedName("U_ATRD_TituloPublicacao")
	private String uTituloPublicacao;

	@SerializedName("U_ATRD_AutPublicidade")
	private String uAutorizacaoPublicidade;

	@SerializedName("U_ATRD_TipoTransacao")
	private String uTipoTransacao;

	@SerializedName("U_ATRD_AnuncianteEndereco")
	private String uAnuncianteEndereco;

	@SerializedName("U_ATRD_AnuncianteCEP")
	private String uAnuncianteCEP;

	@SerializedName("U_ATRD_AnuncianteCidade")
	private String uAnuncianteCidade;

	@SerializedName("U_ATRD_AnuncianteEstado")
	private String uAnuncianteEstado;

	@SerializedName("U_ATRD_AnuncianteIdent")
	private String uAnuncianteIdentificador;

	@SerializedName("U_ATRD_AnuncianteNome")
	private String uAnuncianteNome;

	@SerializedName("U_ATRD_AnuncianteInscEst")
	private String uAnuncianteInscricaoEstadual;

	@SerializedName("U_ATRD_PeriodoVeiculacao")
	private String uPeriodoVeiculacao;

	@SerializedName("U_ATRD_DtContrato")
	private String uDataContrato;

	@SerializedName("U_ATRD_PageViews")
	private Integer uPageViews;

	@SerializedName("U_ATRD_EntregaVendedor")
	private String uEntregaVendedor;

	@SerializedName("U_ATRD_Produto")
	private String uProduto;

	@SerializedName("U_ATRD_Campanha")
	private String uCampanha;

	@SerializedName("U_ATRD_PostoId")
	private String uPostoId;

	@SerializedName("U_ATRD_DtPublicacaoIni")
	private String uDataPublicacaoInicial;

	@SerializedName("U_ATRD_Observacao")
	private String uObservacao;

	@SerializedName("U_ATRD_Permuta")
	private String uPermuta;

	public List<RadioNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<RadioNotaFiscalSaidaLinhaModel> linhas) {
		this.linhas = linhas;
	}

	public String getUEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setUEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public String getUAnuncianteId() {
		return uAnuncianteId;
	}

	public void setUAnuncianteId(String uAnuncianteId) {
		this.uAnuncianteId = uAnuncianteId;
	}

	public String getUNumeroPI() {
		return uNumeroPI;
	}

	public void setUNumeroPI(String uNumeroPI) {
		this.uNumeroPI = uNumeroPI;
	}

	public Double getUComissaoAgencia() {
		return uComissaoAgencia;
	}

	public void setUComissaoAgencia(Double uComissaoAgencia) {
		this.uComissaoAgencia = uComissaoAgencia;
	}

	public String getUDataPublicacaoFinal() {
		return uDataPublicacaoFinal;
	}

	public void setUDataPublicacaoFinal(String uDataPublicacaoFinal) {
		this.uDataPublicacaoFinal = uDataPublicacaoFinal;
	}

	public String getUDiasPublicacao() {
		return uDiasPublicacao;
	}

	public void setUDiasPublicacao(String uDiasPublicacao) {
		this.uDiasPublicacao = uDiasPublicacao;
	}

	public Double getUValorBruto() {
		return uValorBruto;
	}

	public void setUValorBruto(Double uValorBruto) {
		this.uValorBruto = uValorBruto;
	}

	public String getUTituloPublicacao() {
		return uTituloPublicacao;
	}

	public void setUTituloPublicacao(String uTituloPublicacao) {
		this.uTituloPublicacao = uTituloPublicacao;
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

	public String getUAnuncianteEndereco() {
		return uAnuncianteEndereco;
	}

	public void setUAnuncianteEndereco(String uAnuncianteEndereco) {
		this.uAnuncianteEndereco = uAnuncianteEndereco;
	}

	public String getUAnuncianteCEP() {
		return uAnuncianteCEP;
	}

	public void setUAnuncianteCEP(String uAnuncianteCEP) {
		this.uAnuncianteCEP = uAnuncianteCEP;
	}

	public String getUAnuncianteCidade() {
		return uAnuncianteCidade;
	}

	public void setUAnuncianteCidade(String uAnuncianteCidade) {
		this.uAnuncianteCidade = uAnuncianteCidade;
	}

	public String getUAnuncianteEstado() {
		return uAnuncianteEstado;
	}

	public void setUAnuncianteEstado(String uAnuncianteEstado) {
		this.uAnuncianteEstado = uAnuncianteEstado;
	}

	public String getUAnuncianteIdentificador() {
		return uAnuncianteIdentificador;
	}

	public void setUAnuncianteIdentificador(String uAnuncianteIdentificador) {
		this.uAnuncianteIdentificador = uAnuncianteIdentificador;
	}

	public String getUAnuncianteNome() {
		return uAnuncianteNome;
	}

	public void setUAnuncianteNome(String uAnuncianteNome) {
		this.uAnuncianteNome = uAnuncianteNome;
	}

	public String getUAnuncianteInscricaoEstadual() {
		return uAnuncianteInscricaoEstadual;
	}

	public void setUAnuncianteInscricaoEstadual(String uAnuncianteInscricaoEstadual) {
		this.uAnuncianteInscricaoEstadual = uAnuncianteInscricaoEstadual;
	}

	public String getUPeriodoVeiculacao() {
		return uPeriodoVeiculacao;
	}

	public void setUPeriodoVeiculacao(String uPeriodoVeiculacao) {
		this.uPeriodoVeiculacao = uPeriodoVeiculacao;
	}

	public String getUDataContrato() {
		return uDataContrato;
	}

	public void setUDataContrato(String uDataContrato) {
		this.uDataContrato = uDataContrato;
	}

	public Integer getUPageViews() {
		return uPageViews;
	}

	public void setUPageViews(Integer uPageViews) {
		this.uPageViews = uPageViews;
	}

	public String getUEntregaVendedor() {
		return uEntregaVendedor;
	}

	public void setUEntregaVendedor(String uEntregaVendedor) {
		this.uEntregaVendedor = uEntregaVendedor;
	}

	public String getUProduto() {
		return uProduto;
	}

	public void setUProduto(String uProduto) {
		this.uProduto = uProduto;
	}

	public String getUCampanha() {
		return uCampanha;
	}

	public void setUCampanha(String uCampanha) {
		this.uCampanha = uCampanha;
	}

	public String getUPostoId() {
		return uPostoId;
	}

	public void setUPostoId(String uPostoId) {
		this.uPostoId = uPostoId;
	}

	public String getUDataPublicacaoInicial() {
		return uDataPublicacaoInicial;
	}

	public void setUDataPublicacaoInicial(String uDataPublicacaoInicial) {
		this.uDataPublicacaoInicial = uDataPublicacaoInicial;
	}

	public String getUPermuta() {
		return uPermuta;
	}

	public void setUPermuta(String uPermuta) {
		this.uPermuta = uPermuta;
	}

	public List<RadioParcelaNotaFiscalSaidaModel> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<RadioParcelaNotaFiscalSaidaModel> parcelas) {
		this.parcelas = parcelas;
	}

}
