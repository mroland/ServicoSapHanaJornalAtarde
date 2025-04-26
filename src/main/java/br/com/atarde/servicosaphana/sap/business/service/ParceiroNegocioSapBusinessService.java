/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import java.util.ArrayList;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.sap.dao.FormaPagamentoDAO;
import br.com.atarde.servicosaphana.sap.dao.IdentificadorFiscalDAO;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.FormaPagamentoModel;
import br.com.atarde.servicosaphana.sap.hana.model.IdentificadorFiscalModel;
import br.com.atarde.servicosaphana.sap.hana.model.ParceiroNegocioEnderecoModel;
import br.com.atarde.servicosaphana.sap.hana.model.ParceiroNegocioModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.FormaPagamento;
import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ParceiroNegocioSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSessaoHanaModel = new ConexaoSapUtil().getConnection(this.empresa);

		}

	}

	public ParceiroNegocio inserirComEndereco(ParceiroNegocio model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		ParceiroNegocioModel parceiroNegocioJsonModel = new ParceiroNegocioModel();

		this.inserirCliente(model, parceiroNegocioJsonModel);

		this.inserirFormaPagamento(model, parceiroNegocioJsonModel);

		IdentificadorFiscal identificadorFiscal;

		identificadorFiscal = new IdentificadorFiscal();

		identificadorFiscal.setParceiroNegocio(model);

		identificadorFiscal.setEmpresa(model.getEmpresa());

		if (TSUtil.isEmpty(new IdentificadorFiscalDAO().obterEnderecoNulo(identificadorFiscal))) {

			// para address='' --> default

			model.getIdentificadorFiscal().setEnderecoId("");

			this.inserirIdentificadorFiscal(identificadorFiscal, parceiroNegocioJsonModel, "bo_ShipTo");

		}

		model.getEndereco().setTipoEndereco("S");

		model.getEndereco().setId("Entrega" /* + " - " + model.getId() */ + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

		this.inserirEndereco(model, parceiroNegocioJsonModel);

		model.getEndereco().setTipoEndereco("B");

		model.getEndereco().setId("Cobranca" /* + " - " + model.getId() */ + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

		this.inserirEndereco(model, parceiroNegocioJsonModel);

		// this.inserirFiliais(model, parceiroNegocioJsonModel);

		parceiroNegocioJsonModel = this.inserir(parceiroNegocioJsonModel, this.conexaoSessaoHanaModel);

		model.setId(parceiroNegocioJsonModel.getId());

		return model;

	}

	private ParceiroNegocioModel inserir(ParceiroNegocioModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		// System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/BusinessPartners").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		ParceiroNegocioModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, ParceiroNegocioModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			// System.out.println(jsonString);

			RetornoSapErroModel retorno = new Gson().fromJson(jsonString, RetornoSapErroModel.class);

			throw new Exception(retorno.getErroModel().getMensagemErroModel().getValor());

		}

		return resposta;

	}

	public ParceiroNegocio inserirSemEndereco(ParceiroNegocio model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		ParceiroNegocioModel parceiroNegocioJsonModel = new ParceiroNegocioModel();

		this.inserirCliente(model, parceiroNegocioJsonModel);

		this.inserirFormaPagamento(model, parceiroNegocioJsonModel);

		IdentificadorFiscal identificadorFiscalModel;

		identificadorFiscalModel = new IdentificadorFiscal();

		model.getIdentificadorFiscal().setEnderecoId("");

		identificadorFiscalModel.setParceiroNegocio(model);

		this.inserirIdentificadorFiscal(identificadorFiscalModel, parceiroNegocioJsonModel, "bo_ShipTo");

		// this.inserirFiliais(model, parceiroNegocioJsonModel);

		parceiroNegocioJsonModel = this.inserir(parceiroNegocioJsonModel, this.conexaoSessaoHanaModel);

		model.setId(parceiroNegocioJsonModel.getId());

		return model;

	}

	public ParceiroNegocio inserirEnderecoCliente(ParceiroNegocio model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());

		ParceiroNegocioModel parceiroNegocioJsonModel = new ParceiroNegocioModel();

		parceiroNegocioJsonModel.setId(model.getId());

		parceiroNegocioJsonModel = this.obter(parceiroNegocioJsonModel, this.conexaoSessaoHanaModel);

		if (!TSUtil.isEmpty(parceiroNegocioJsonModel)) {

			this.inserirEndereco(model, parceiroNegocioJsonModel);

			this.atualizar(parceiroNegocioJsonModel, this.conexaoSessaoHanaModel);

			return model;

		}

		return null;

	}

	private void atualizar(ParceiroNegocioModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws TSApplicationException {

		// System.out.println(new Gson().toJson(model));

		WebTarget target = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/BusinessPartners" + "('" + model.getId() + "')");

		target.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId());

		Response response = invocationBuilder.method(HttpMethod.PATCH, Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		if (response.getStatusInfo().getStatusCode() != 204) {

			String jsonString = response.readEntity(String.class);

			// System.out.println(jsonString);

			throw new TSApplicationException(new Exception("Erro de conexão com SAP. Erro" + jsonString));

		}

	}

	private ParceiroNegocioModel obter(ParceiroNegocioModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws TSApplicationException {

		// System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/BusinessPartners" + "('" + model.getId() + "')?$select=CardCode,CardName,CardType,BPFiscalTaxIDCollection,BPAddresses").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).get();

		ParceiroNegocioModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, ParceiroNegocioModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			// System.out.println(jsonString);

			throw new TSApplicationException(new Exception("Erro de conexão com SAP. Erro" + jsonString));

		}

		return resposta;

	}

	private void inserirCliente(ParceiroNegocio model, ParceiroNegocioModel parceiroNegocioJsonModel) {

		parceiroNegocioJsonModel.setNome(model.getNome());

		if (Constantes.TIPO_PARCEIRO_NEGOCIO_CLIENTE.equals(model.getTipo())) {

			parceiroNegocioJsonModel.setTipo("cCustomer");
			parceiroNegocioJsonModel.setSerie(71);

			// parceiroNegocioJsonModel.setId("C" + model.getIdentificadorFiscal().getIdentificador());

		} else {

			parceiroNegocioJsonModel.setTipo("cSupplier");
			parceiroNegocioJsonModel.setSerie(72);

			// parceiroNegocioJsonModel.setId("F" + model.getIdentificadorFiscal().getIdentificador());

		}

		if (!TSUtil.isEmpty(model.getNomeFantasia())) {

			parceiroNegocioJsonModel.setNomeFantasia(model.getNomeFantasia());

		}

		if (!TSUtil.isEmpty(model.getTelefoneResidencial())) {

			parceiroNegocioJsonModel.setTelefoneResidencial(model.getTelefoneResidencial());

		}

		if (!TSUtil.isEmpty(model.getTelefoneCelular())) {

			parceiroNegocioJsonModel.setTelefoneCelular(model.getTelefoneCelular());

		}

		if (!TSUtil.isEmpty(model.getFax())) {

			parceiroNegocioJsonModel.setFax(model.getFax());

		}

		if (!TSUtil.isEmpty(model.getEmail())) {

			parceiroNegocioJsonModel.setEmail(model.getEmail());

		}

		if (!TSUtil.isEmpty(model.getObservacao())) {

			parceiroNegocioJsonModel.setObservacao(model.getObservacao());

		}

		if (!TSUtil.isEmpty(model.getClassificacao()) && !TSUtil.isEmpty(model.getClassificacao().getId())) {

			parceiroNegocioJsonModel.setClassificacao(model.getClassificacao().getId().intValue());

		}

		model.setId(parceiroNegocioJsonModel.getId());

	}

	private void inserirFormaPagamento(ParceiroNegocio model, ParceiroNegocioModel parceiroNegocioJsonModel) {

		FormaPagamento forma = new FormaPagamento();

		forma.setEmpresa(model.getEmpresa());

		if (Constantes.TIPO_PARCEIRO_NEGOCIO_CLIENTE.equals(model.getTipo())) {

			forma.setTipo("I");

		} else {

			forma.setTipo("O");

		}

		for (FormaPagamento item : new FormaPagamentoDAO().pesquisar(forma)) {

			if (TSUtil.isEmpty(parceiroNegocioJsonModel.getFormasPagamentos())) {

				parceiroNegocioJsonModel.setFormasPagamentos(new ArrayList<FormaPagamentoModel>());

			}

			FormaPagamentoModel formaPagamento = new FormaPagamentoModel();
			formaPagamento.setId(item.getId());

			parceiroNegocioJsonModel.getFormasPagamentos().add(formaPagamento);

		}

	}

	private void inserirEndereco(ParceiroNegocio model, ParceiroNegocioModel parceiroNegocioJsonModel) {

		if (TSUtil.isEmpty(parceiroNegocioJsonModel.getEnderecos())) {

			parceiroNegocioJsonModel.setEnderecos(new ArrayList<ParceiroNegocioEnderecoModel>());

		}

		ParceiroNegocioEnderecoModel endereco = new ParceiroNegocioEnderecoModel();

		endereco.setId(model.getEndereco().getId());

		if (model.getEndereco().getTipoEndereco().equals("S")) {

			// para address = ShipTo

			endereco.setTipoEndereco("bo_ShipTo");

			IdentificadorFiscal identificadorFiscalModel = new IdentificadorFiscal();

			model.getIdentificadorFiscal().setEnderecoId(endereco.getId());

			identificadorFiscalModel.setParceiroNegocio(model);

			this.inserirIdentificadorFiscal(identificadorFiscalModel, parceiroNegocioJsonModel, endereco.getTipoEndereco());

		} else {

			// BillTo

			endereco.setTipoEndereco("bo_BillTo");

			IdentificadorFiscal identificadorFiscalModel = new IdentificadorFiscal();

			model.getIdentificadorFiscal().setEnderecoId(endereco.getId());

			identificadorFiscalModel.setParceiroNegocio(model);

			this.inserirIdentificadorFiscal(identificadorFiscalModel, parceiroNegocioJsonModel, endereco.getTipoEndereco());

		}

		if (!TSUtil.isEmpty(model.getEndereco().getTipoLogradouro())) {

			endereco.setTipoLogradouro(model.getEndereco().getTipoLogradouro());

		} else {

			// endereco.setTipoLogradouro("RUA");
			// colocado provisioriamente
		}

		if (!TSUtil.isEmpty(model.getEndereco().getBairro())) {

			endereco.setBairro(model.getEndereco().getBairro());

		}

		endereco.setLogradouro(model.getEndereco().getLogradouro());

		endereco.setMunicipio(model.getEndereco().getMunicipio().getId().toString());

		endereco.setNumero(model.getEndereco().getNumero());

		if (!TSUtil.isEmpty(model.getEndereco().getComplemento())) {

			endereco.setComplemento(model.getEndereco().getComplemento());

		}

		endereco.setCidade(model.getEndereco().getCidade());

		endereco.setCep(model.getEndereco().getCep());

		endereco.setEstado(model.getEndereco().getEstado().getId());

		endereco.setPais(model.getEndereco().getPais().getId());

		endereco.setPaceiroNegocioId(parceiroNegocioJsonModel.getId());

		// endereco.setDataCriacao(TSParseUtil.dateToString(new Date(), "yyyy-MM-dd"));

		// endereco.setHoraCriacao(TSParseUtil.dateToString(new Date(), "HH:mm:ss"));

		// endereco.setLinha(linha);

		parceiroNegocioJsonModel.getEnderecos().add(endereco);

	}

	private void inserirIdentificadorFiscal(IdentificadorFiscal model, ParceiroNegocioModel parceiroNegocioJsonModel, String tipoEndereco) {

		if (TSUtil.isEmpty(parceiroNegocioJsonModel.getIdentificadoresFiscais())) {

			parceiroNegocioJsonModel.setIdentificadoresFiscais(new ArrayList<IdentificadorFiscalModel>());

		}

		IdentificadorFiscalModel fiscalModel = new IdentificadorFiscalModel();

		fiscalModel.setEnderecoId(model.getParceiroNegocio().getIdentificadorFiscal().getEnderecoId());

		if (TSUtil.isEmpty(tipoEndereco)) {

			fiscalModel.setTipoEndereco("bo_ShipTo");

		} else {

			fiscalModel.setTipoEndereco(tipoEndereco);

		}

		fiscalModel.setParceiroNegocioId(parceiroNegocioJsonModel.getId());

		switch (model.getParceiroNegocio().getIdentificadorFiscal().getTipoIdentificador().intValue()) {

		case 0:// CNPJ

			fiscalModel.setCnpj(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		case 1:// CPF

			fiscalModel.setCpf(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		case 2:// OUTROS

			fiscalModel.setOutros(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual())) {

			fiscalModel.setInscricaoEstadual(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria())) {

			fiscalModel.setInscricaoEstadualSubstitutoTributaria(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal())) {

			fiscalModel.setInscricaoMunicipal(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS())) {

			fiscalModel.setInscricaoINSS(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS());

		}

		parceiroNegocioJsonModel.getIdentificadoresFiscais().add(fiscalModel);

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ConexaoSessaoHanaModel getConexaoSessaoHanaModel() {
		return conexaoSessaoHanaModel;
	}

	public void setConexaoSessaoHanaModel(ConexaoSessaoHanaModel conexaoSessaoHanaModel) {
		this.conexaoSessaoHanaModel = conexaoSessaoHanaModel;
	}

}
