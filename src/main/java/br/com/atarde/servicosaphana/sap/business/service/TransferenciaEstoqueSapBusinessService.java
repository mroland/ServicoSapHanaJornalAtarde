package br.com.atarde.servicosaphana.sap.business.service;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.model.TransferenciaEstoque;
import br.com.atarde.servicosaphana.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.hana.model.TransferenciaEstoqueLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.TransferenciaEstoqueModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public TransferenciaEstoque inserir(TransferenciaEstoque model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		TransferenciaEstoqueModel nffJson = new TransferenciaEstoqueModel();

		// nffJson.setIdExterno(model.getIdExterno());

		nffJson.setFilialId(model.getFilial().getId());

		nffJson.setDataDocumento(TSParseUtil.dateToString(model.getDataDocumento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		nffJson.setDataVencimento(TSParseUtil.dateToString(model.getDataVencimento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		nffJson.setDataLancamento(TSParseUtil.dateToString(model.getDataLancamento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		nffJson.setUOrigem(Integer.valueOf(model.getOrigem().getId().toString()));

		nffJson.setObservacao(model.getObservacao());

		nffJson.setObservacaoDiario(model.getObservacaoDiario());

		nffJson.setDepositoOrigemId(model.getEstoqueOrigem().getId());

		nffJson.setDepositoDestinoId(model.getEstoqueDestino().getId());

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<TransferenciaEstoqueLinhaModel>());

		}

		TransferenciaEstoqueLinhaModel linhaJson;
		for (TransferenciaEstoqueLinha linha : model.getLinhas()) {

			linhaJson = new TransferenciaEstoqueLinhaModel();

			linhaJson.setItemId(linha.getItem().getId());

			linhaJson.setQuantidade(linha.getQuantidade());

			nffJson.getLinhas().add(linhaJson);

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		model.setId(nffJson.getId());

		return model;

	}

	private TransferenciaEstoqueModel inserir(TransferenciaEstoqueModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		// System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/StockTransfers").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		TransferenciaEstoqueModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, TransferenciaEstoqueModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			// System.out.println(jsonString);

			RetornoSapErroModel retorno = new Gson().fromJson(jsonString, RetornoSapErroModel.class);

			throw new Exception(retorno.getErroModel().getMensagemErroModel().getValor());

		}

		return resposta;

	}

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSessaoHanaModel = new ConexaoSapUtil().getConnection(this.empresa);

		}

	}

}
