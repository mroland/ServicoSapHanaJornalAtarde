package br.com.atarde.servicosaphana.sap.business.service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.hana.model.TabelaUsuarioMovimentacaoModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public TabelaUsuarioMovimentacao inserir(TabelaUsuarioMovimentacao model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		TabelaUsuarioMovimentacaoModel nffJson = new TabelaUsuarioMovimentacaoModel();

		nffJson.setIdExterno(model.getIdExterno());

		nffJson.setId(model.getId());

		nffJson.setNome(model.getId());

		nffJson.setTipoMovimentacao(model.getTipoMovimentacao());

		nffJson.setItemId(model.getItem().getId());

		nffJson.setQuantidade(model.getQuantidade());

		if (!TSUtil.isEmpty(model.getSapNotaFiscalSaidaId())) {

			nffJson.setDocentry(model.getSapNotaFiscalSaidaId());

		} else if (!TSUtil.isEmpty(model.getSapDevolucaoNotaFiscalSaidaId())) {

			nffJson.setDocentry(model.getSapDevolucaoNotaFiscalSaidaId());

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		return model;

	}

	private TabelaUsuarioMovimentacaoModel inserir(TabelaUsuarioMovimentacaoModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		// System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/U_ATRD_MOVVDA").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		TabelaUsuarioMovimentacaoModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, TabelaUsuarioMovimentacaoModel.class);

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
