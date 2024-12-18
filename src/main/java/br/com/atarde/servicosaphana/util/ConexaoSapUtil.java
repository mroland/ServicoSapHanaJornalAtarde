package br.com.atarde.servicosaphana.util;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.dao.ConexaoSessaoHanaDAO;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSapHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ConexaoSapUtil {

	public ConexaoSessaoHanaModel getConnection(Empresa empresa) throws TSApplicationException {

		ConexaoSessaoHanaModel conexaoSessaoModel = new ConexaoSessaoHanaDAO().obter(empresa);

		boolean expirou = false;

		if (TSUtil.isEmpty(conexaoSessaoModel)) {

			conexaoSessaoModel = this.obter(empresa);

		} else if (conexaoSessaoModel.isExpirou(new Date())) {
			
			Long id = conexaoSessaoModel.getId();

			conexaoSessaoModel = this.obter(empresa);
			
			conexaoSessaoModel.setId(id);

			expirou = true;

		}

		if (expirou) {

			this.popularConexaoModel(conexaoSessaoModel, empresa);

			new ConexaoSessaoHanaDAO().alterar(conexaoSessaoModel);

		} else {

			if (TSUtil.isEmpty(conexaoSessaoModel.getId())) {

				this.popularConexaoModel(conexaoSessaoModel, empresa);

				new ConexaoSessaoHanaDAO().inserir(conexaoSessaoModel);

			}

		}

		return conexaoSessaoModel;

	}

	private void popularConexaoModel(ConexaoSessaoHanaModel conexaoSessaoModel, Empresa empresaModel) {

		conexaoSessaoModel.setDataInicial(new Date());

		conexaoSessaoModel.setDataExpiracao(this.obterDataExpiracao(conexaoSessaoModel.getDataInicial(), Integer.parseInt(conexaoSessaoModel.getSessaoTimeout())));

		conexaoSessaoModel.setEmpresaModel(empresaModel);

	}

	private Date obterDataExpiracao(Date dataReferencia, int tempoMinutos) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dataReferencia);

		calendar.add(Calendar.MINUTE, tempoMinutos);

		return calendar.getTime();

	}

	private ConexaoSessaoHanaModel obter(Empresa empresa) throws TSApplicationException {

		ConexaoSessaoHanaModel resposta = new ConexaoSessaoHanaModel();

		ConexaoSapHanaModel conexaoSapHana = this.obterConexao(empresa);

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(empresa.getUrlSapHana()) + "/Login").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).post(Entity.entity(new Gson().toJson(conexaoSapHana), MediaType.APPLICATION_JSON_TYPE));

		if (response.getStatusInfo().getStatusCode() == 200) {

			String json = response.readEntity(String.class);

			System.out.println(json);

			resposta = new Gson().fromJson(json, ConexaoSessaoHanaModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			System.out.println(jsonString);

			throw new TSApplicationException(new Exception("Erro de conexão com SAP. Erro" + jsonString));

		}

		return resposta;

	}

	private ConexaoSapHanaModel obterConexao(Empresa empresa) {

		ConexaoSapHanaModel model = new ConexaoSapHanaModel();

		model.setInstancia(empresa.getDbInstancia());

		model.setSenha(empresa.getAppSenha());

		model.setUsuario(empresa.getAppUsuario());
		
		model.setLinguagem(29); //portugues

		return model;

	}

}
