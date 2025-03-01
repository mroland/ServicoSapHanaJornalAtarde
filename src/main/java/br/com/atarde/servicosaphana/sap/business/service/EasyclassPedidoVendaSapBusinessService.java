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

import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaLinha;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.EasyclassPedidoVendaLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.EasyclassPedidoVendaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class EasyclassPedidoVendaSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSessaoHanaModel = new ConexaoSapUtil().getConnection(this.empresa);

		}

	}

	public EasyclassPedidoVenda inserir(EasyclassPedidoVenda model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		EasyclassPedidoVendaModel nffJson = new EasyclassPedidoVendaModel();

		nffJson.setParceiroNegocioId(model.getCliente().getId());

		nffJson.setIdExterno(model.getIdExterno());

		nffJson.setFilialId(model.getFilial().getId());

		nffJson.setDataDocumento(TSParseUtil.dateToString(model.getDataDocumento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {

			// pegar a condicao de pagamento
			nffJson.setCondicaoPagamentoId(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));

		} else {

			nffJson.setDataVencimento(TSParseUtil.dateToString(model.getDataVencimento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		}

		nffJson.setDataLancamento(TSParseUtil.dateToString(model.getDataLancamento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		nffJson.setVendedorId(model.getVendedor().getId().intValue());

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoEntregaDefault())) {

			nffJson.setEnderecoEntregaId(model.getCliente().getEnderecoEntregaDefault());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoCobrancaDefault())) {

			nffJson.setEnderecoCobrancaId(model.getCliente().getEnderecoCobrancaDefault());

		}


		//nffJson.setObservacao(model.getObservacao());

		//nffJson.setIncoterms("0");

		nffJson.setUOrigem(model.getOrigem().getId().intValue());

		nffJson.setUAnuncianteId(model.getCliente().getId());

		nffJson.setUEnderecoEntrega(model.getUEnderecoEntrega());

		if (!TSUtil.isEmpty(model.getUNumeroPI())) {

			nffJson.setUNumeroPI(model.getUNumeroPI());

		}

		nffJson.setSequenciaId(Integer.valueOf(model.getSequencia().getId().toString()));

		nffJson.setUComissaoAgencia(model.getUComissaoAgencia().doubleValue());

		nffJson.setUDataPublicacaoFinal(TSParseUtil.dateToString(model.getUDataPublicacaoFinal(), TSDateUtil.YYYY_MM_DD));

		nffJson.setUDiasPublicacao(model.getUDiasPublicacao());

		nffJson.setUValorBruto(model.getUValorBruto().doubleValue());

		nffJson.setUTituloPublicacao(model.getUTituloPublicacao());

		if (!TSUtil.isEmpty(model.getUAutorizacaoPublicidade())) {

			nffJson.setUAutorizacaoPublicidade(model.getUAutorizacaoPublicidade());

		}

		if (!TSUtil.isEmpty(model.getUTipoTransacao())) {

			nffJson.setUTipoTransacao(model.getUTipoTransacao());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEndereco().getLogradouro())) {

			String endereco;

			endereco = model.getCliente().getEndereco().getLogradouro().concat(" ".concat(model.getCliente().getEndereco().getNumero()));

			if (!TSUtil.isEmpty(model.getCliente().getEndereco().getComplemento())) {

				endereco.concat(" ".concat(model.getCliente().getEndereco().getComplemento()));
			}

			if (!TSUtil.isEmpty(model.getCliente().getEndereco().getBairro())) {

				endereco.concat(" ".concat(model.getCliente().getEndereco().getBairro()));
			}

			endereco = TSStringUtil.rightPad(endereco, 254, " ").substring(0, 253).trim();

			nffJson.setUAnuncianteEndereco(endereco);

		}

		if (!TSUtil.isEmpty(model.getCliente().getEndereco().getCep())) {

			nffJson.setUAnuncianteCEP(model.getCliente().getEndereco().getCep());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEndereco().getCidade())) {

			nffJson.setUAnuncianteCidade(model.getCliente().getEndereco().getCidade());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEndereco().getEstado())) {

			nffJson.setUAnuncianteEstado(model.getCliente().getEndereco().getEstado().getId());

		}

		if (!TSUtil.isEmpty(model.getCliente().getIdentificadorFiscal().getIdentificador())) {

			nffJson.setUAnuncianteIdentificador(model.getCliente().getIdentificadorFiscal().getIdentificador());

		}

		if (!TSUtil.isEmpty(model.getCliente().getNome())) {

			nffJson.setUAnuncianteNome(model.getCliente().getNome());

		}

		if (!TSUtil.isEmpty(model.getCliente().getIdentificadorFiscal().getInscricaoEstadual())) {

			nffJson.setUAnuncianteInscricaoEstadual(model.getCliente().getIdentificadorFiscal().getInscricaoEstadual());

		}

		if (!TSUtil.isEmpty(model.getUPeriodo())) {

			nffJson.setUPeriodo(model.getUPeriodo());

		}

		if (!TSUtil.isEmpty(model.getUPageViews())) {

			nffJson.setUPageViews(model.getUPageViews().intValue());

		}

		if (!TSUtil.isEmpty(model.getUFormato())) {

			nffJson.setUFormato(model.getUFormato());

		}

		if (!TSUtil.isEmpty(model.getUEntregaVendedor())) {

			nffJson.setUEntregaVendedor(model.getUEntregaVendedor());

		}

		if (!TSUtil.isEmpty(model.getUProduto())) {

			nffJson.setUProduto(model.getUProduto());

		}

		if (!TSUtil.isEmpty(model.getUCampanha())) {

			nffJson.setUCampanha(model.getUCampanha());

		}

		if (!TSUtil.isEmpty(model.getUPostoId())) {

			nffJson.setUPostoId(model.getUPostoId());

		}

		if (!TSUtil.isEmpty(model.getUDataPublicacaoInicial())) {

			nffJson.setUDataPublicacaoInicial(TSParseUtil.dateToString(model.getUDataPublicacaoInicial(), TSDateUtil.DD_MM_YYYY));

		}

		if (!TSUtil.isEmpty(model.getUObservacao())) {

			nffJson.setUObservacao(model.getUObservacao());

		}

		if (!TSUtil.isEmpty(model.getUPermuta())) {

			nffJson.setUPermuta(model.getUPermuta().toString());

		}

		// linhas do titulo

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<EasyclassPedidoVendaLinhaModel>());

		}

		EasyclassPedidoVendaLinhaModel linhaJson;
		for (EasyclassPedidoVendaLinha linha : model.getLinhas()) {

			linhaJson = new EasyclassPedidoVendaLinhaModel();

			linhaJson.setItemId(linha.getItem().getId());

			linhaJson.setQuantidade(linha.getQuantidade());

			linhaJson.setValor(linha.getValor().doubleValue());

			linhaJson.setCodigoImpostoId(linha.getCodigoImposto().getId()); // fixo

			linhaJson.setUtilizacaoId(linha.getUtilizacao().getId().toString()); /// fixo

			linhaJson.setUArea(linha.getUArea().doubleValue());

			linhaJson.setUCmXCol(linha.getUCmXCol());

			linhaJson.setUQuantidadeInsercoes(linha.getUQuantidadeInsercoes());

			linhaJson.setUTotalCmXCol(linha.getUTotalCmXCol().doubleValue());

			linhaJson.setUValorUnitario(linha.getUValorUnitario().doubleValue());

			nffJson.getLinhas().add(linhaJson);

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		model.setId(nffJson.getId());

		return model;

	}

	private EasyclassPedidoVendaModel inserir(EasyclassPedidoVendaModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		//System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/Orders").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		EasyclassPedidoVendaModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			//System.out.println(json);

			resposta = new Gson().fromJson(json, EasyclassPedidoVendaModel.class);

		} else {

			String jsonString = response.readEntity(String.class);

			//System.out.println(jsonString);

			RetornoSapErroModel retorno = new Gson().fromJson(jsonString, RetornoSapErroModel.class);

			throw new Exception(retorno.getErroModel().getMensagemErroModel().getValor());

		}

		return resposta;

	}

}
