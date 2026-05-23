/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVenda;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVendaLinha;
import br.com.atarde.servicosaphana.sap.hana.model.ClassificadosExecucaoPedidoVendaLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.ClassificadosExecucaoPedidoVendaModel;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ClassificadosExecucaoPedidoVendaSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSessaoHanaModel = new ConexaoSapUtil().getConnection(this.empresa);

		}

	}

	public ClassificadosExecucaoPedidoVenda inserir(ClassificadosExecucaoPedidoVenda model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		ClassificadosExecucaoPedidoVendaModel nffJson = new ClassificadosExecucaoPedidoVendaModel();

		nffJson.setParceiroNegocioId(model.getCliente().getId());

		nffJson.setIdExterno(model.getIdExterno());

		nffJson.setFilialId(model.getFilial().getId());

		nffJson.setDataDocumento(TSParseUtil.dateToString(model.getDataDocumento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		//e setar sempre a data de vencimento(data de entrega) a data de documento
		nffJson.setDataVencimento(nffJson.getDataDocumento());

		if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {

			// pegar a condicao de pagamento
			nffJson.setCondicaoPagamentoId(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));
			
		} else {
			
			//quando tiver data de vencimento colocar no comments para pedido de vendas apenas
			
			nffJson.setObservacao("Vencimento: " + TSParseUtil.dateToString(model.getDataVencimento(), TSDateUtil.DD_MM_YYYY));
			
			//nffJson.setDataVencimento(TSParseUtil.dateToString(model.getDataVencimento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		}

		nffJson.setDataLancamento(TSParseUtil.dateToString(model.getDataLancamento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		nffJson.setVendedorId(model.getVendedor().getId().intValue());

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoEntregaDefault())) {

			nffJson.setEnderecoEntregaId(model.getCliente().getEnderecoEntregaDefault());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoCobrancaDefault())) {

			nffJson.setEnderecoCobrancaId(model.getCliente().getEnderecoCobrancaDefault());

		}


		nffJson.setUOrigem(model.getOrigem().getId().intValue());

		nffJson.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nffJson.setSequenciaId(Integer.valueOf(model.getSequencia().getId().toString()));

		nffJson.setUValorBruto(model.getUValorBruto().doubleValue());

		// linhas do titulo

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<ClassificadosExecucaoPedidoVendaLinhaModel>());

		}

		ClassificadosExecucaoPedidoVendaLinhaModel linhaJson;
		for (ClassificadosExecucaoPedidoVendaLinha linha : model.getLinhas()) {

			linhaJson = new ClassificadosExecucaoPedidoVendaLinhaModel();

			linhaJson.setItemId(linha.getItem().getId());

			linhaJson.setQuantidade(linha.getQuantidade());

			linhaJson.setValor(linha.getValor().doubleValue());

			linhaJson.setCodigoImpostoId(linha.getCodigoImposto().getId()); // fixo

			linhaJson.setUtilizacaoId(linha.getUtilizacao().getId().toString()); /// fixo
			
			linhaJson.setDepositoId(linha.getEstoque().getId());
			
			linhaJson.setUnidadeNegocioId(linha.getUnidadeNegocio().getId());
			
			if (!TSUtil.isEmpty(linha.getContaContabil().getId())) {

				linhaJson.setContaContabilId(linha.getContaContabil().getId());

			}

			nffJson.getLinhas().add(linhaJson);

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		model.setId(nffJson.getId());
		
		model.setArquivoRemessaSap(nffJson.getArquivoRemessaSap());

		return model;

	}

	private ClassificadosExecucaoPedidoVendaModel inserir(ClassificadosExecucaoPedidoVendaModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		//System.out.println(new Gson().toJson(model));
		String arquivoRemessaSap = new Gson().toJson(model);

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/Orders").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(arquivoRemessaSap, MediaType.APPLICATION_JSON_TYPE));

		ClassificadosExecucaoPedidoVendaModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			//System.out.println(json);

			resposta = new Gson().fromJson(json, ClassificadosExecucaoPedidoVendaModel.class);
			
			resposta.setArquivoRemessaSap(arquivoRemessaSap);

		} else {

			String jsonString = response.readEntity(String.class);

			//System.out.println(jsonString);

			RetornoSapErroModel retorno = new Gson().fromJson(jsonString, RetornoSapErroModel.class);

			throw new Exception(retorno.getErroModel().getMensagemErroModel().getValor());

		}

		return resposta;

	}

}
