/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.hana.model.VendedorModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Vendedor;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
public class VendedorSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public Vendedor inserir(Vendedor model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		VendedorModel vendedorJsonModel = new VendedorModel();

		vendedorJsonModel.setIdentificador(model.getIdentificador());

		vendedorJsonModel.setNome(model.getNome().toUpperCase());

		vendedorJsonModel.setGrupoComissao(Integer.valueOf(model.getGrupoComissao().getId().toString()));

		if (!TSUtil.isEmpty(model.getUCga())) {

			vendedorJsonModel.setuCga(model.getUCga());

		}

		//vendedorJsonModel.setuAgrupador(model.getIdentificador());

		if (model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_AGENCIA)) {

			vendedorJsonModel.setPercentualComissao(20D);

		} else if (model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_CORRETORES)) {

			vendedorJsonModel.setPercentualComissao(15D);

		} else if (model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_SVG)) {

			vendedorJsonModel.setPercentualComissao(6D);

		}

		vendedorJsonModel = this.inserir(vendedorJsonModel, this.conexaoSessaoHanaModel);
		
		model.setId(new Long(vendedorJsonModel.getId()));

		return model;

	}
	
	
	private VendedorModel inserir(VendedorModel jsonObjeto, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		//System.out.println(new Gson().toJson(jsonObjeto));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/SalesPersons").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(jsonObjeto), MediaType.APPLICATION_JSON_TYPE));

		VendedorModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			//System.out.println(json);

			resposta = new Gson().fromJson(json, VendedorModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			//System.out.println(jsonString);
			
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
