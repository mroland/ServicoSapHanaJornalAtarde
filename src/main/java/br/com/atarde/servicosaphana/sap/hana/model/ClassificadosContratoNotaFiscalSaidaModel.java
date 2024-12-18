package br.com.atarde.servicosaphana.sap.hana.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class ClassificadosContratoNotaFiscalSaidaModel extends NotaFiscalSaidaModel {

	@SerializedName("DocumentLines")
	private List<ClassificadosContratoNotaFiscalSaidaLinhaModel> linhas;

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

	@SerializedName("U_ATRD_PostoId")
	private String uPostoId;

	@SerializedName("U_ATRD_DtPublicacaoIni")
	private String uDataPublicacaoInicial;

	public List<ClassificadosContratoNotaFiscalSaidaLinhaModel> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ClassificadosContratoNotaFiscalSaidaLinhaModel> linhas) {
		this.linhas = linhas;
	}

	public String getUEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setUEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
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

}