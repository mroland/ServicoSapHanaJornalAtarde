package br.com.atarde.servicosaphana.sap.business.service;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.DevolucaoNotaFiscalSaidaLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.DevolucaoNotaFiscalSaidaModel;
import br.com.atarde.servicosaphana.sap.hana.model.DocumentoReferencia;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.DevolucaoNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class DevolucaoNotaFiscalSaidaSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public DevolucaoNotaFiscalSaida inserir(DevolucaoNotaFiscalSaida model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		DevolucaoNotaFiscalSaidaModel nffJson = new DevolucaoNotaFiscalSaidaModel();

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

		// documents.setShipToCode(new ParceiroNegocioEnderecoDAO().obterPeloTipoEndereco(new ParceiroNegocioEndereco(model.getClienteCobranca(), "tipoEndereco", "S")).getId());

		// documents.setPayToCode(new ParceiroNegocioEnderecoDAO().obterPeloTipoEndereco(new ParceiroNegocioEndereco(model.getClienteCobranca(), "tipoEndereco", "B")).getId());

		nffJson.setUOrigem(Integer.valueOf(model.getOrigem().getId().toString()));

		nffJson.setSequenciaId(Integer.valueOf(model.getSequencia().getId().toString()));

		nffJson.setObservacao(model.getObservacao());

		//nffJson.setIncoterms("0");

		// linhas do titulo

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<DevolucaoNotaFiscalSaidaLinhaModel>());

		}

		// linhas do titulo
		DevolucaoNotaFiscalSaidaLinhaModel linhaJson;
		for (DevolucaoNotaFiscalSaidaLinha linha : model.getLinhas()) {

			linhaJson = new DevolucaoNotaFiscalSaidaLinhaModel();

			// verificar se add numeroLinha

			linhaJson.setItemId(linha.getItem().getId());

			linhaJson.setQuantidade(linha.getQuantidade());

			linhaJson.setValor(linha.getValor().doubleValue());

			linhaJson.setUtilizacaoId(linha.getUtilizacao().getId().toString());

			linhaJson.setCodigoImpostoId(linha.getCodigoImposto().getId());

			linhaJson.setDepositoId(linha.getEstoque().getId());

			linhaJson.setUnidadeNegocioId(linha.getUnidadeNegocio().getId());

			if (!TSUtil.isEmpty(linha.getContaContabil().getId())) {

				linhaJson.setContaContabilId(linha.getContaContabil().getId());

			}

			// if (linha.getFlagImposto()) {

			// linhaJson.setFlagImposto("tYES");

			// }

			nffJson.getLinhas().add(linhaJson);

		}

		if (!TSUtil.isEmpty(model.getTransferenciaEstoqueReferencia())) {

			if (TSUtil.isEmpty(nffJson.getReferencias())) {

				nffJson.setReferencias(new ArrayList<DocumentoReferencia>());

			}

			DocumentoReferencia referencia = new DocumentoReferencia();
			referencia.setTipoReferencia("rot_InventoryTransfer");
			referencia.setId(model.getTransferenciaEstoqueReferencia().getId());

			nffJson.getReferencias().add(referencia);

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		model.setId(nffJson.getId());

		return model;

	}

	private DevolucaoNotaFiscalSaidaModel inserir(DevolucaoNotaFiscalSaidaModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		// System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/CreditNotes").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		DevolucaoNotaFiscalSaidaModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, DevolucaoNotaFiscalSaidaModel.class);

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
