package br.com.atarde.servicosaphana.sap.business.service;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaNotaFiscalSaidaLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaNotaFiscalSaidaModel;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaParcelaNotaFiscalSaidaModel;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class AssinaturaNotaFiscalSaidaSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public AssinaturaNotaFiscalSaida inserir(AssinaturaNotaFiscalSaida model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		AssinaturaNotaFiscalSaidaModel nffJson = new AssinaturaNotaFiscalSaidaModel();

		nffJson.setParceiroNegocioId(model.getCliente().getId());

		nffJson.setIdExterno(model.getIdExterno());

		nffJson.setFilialId(model.getFilial().getId());

		nffJson.setDataDocumento(TSParseUtil.dateToString(model.getDataDocumento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {

			// pegar a condicao de pagamento
			nffJson.setCondicaoPagamentoId(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));

			if (!TSUtil.isEmpty(model.getParcelas())) {

				if (TSUtil.isEmpty(nffJson.getParcelas())) {

					nffJson.setParcelas(new ArrayList<AssinaturaParcelaNotaFiscalSaidaModel>());

				}

				String ultimaDataVencimento = null;
				AssinaturaParcelaNotaFiscalSaidaModel parcelaJsonModel;
				for (ParcelaAB parcela : model.getParcelas()) {

					// verificar se vai add o InstallmentId 0,1,2

					parcelaJsonModel = new AssinaturaParcelaNotaFiscalSaidaModel();

					parcelaJsonModel.setDataVencimento(TSParseUtil.dateToString(parcela.getDataVencimento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

					parcelaJsonModel.setValor(parcela.getValor().doubleValue());

					nffJson.getParcelas().add(parcelaJsonModel);

					//pega a ultima data de vencimento
					ultimaDataVencimento = parcelaJsonModel.getDataVencimento();

				}

				nffJson.setDataVencimento(ultimaDataVencimento);

			}

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

		nffJson.setUOrigem(Integer.valueOf(model.getOrigem().getId().toString()));

		nffJson.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nffJson.setSequenciaId(Integer.valueOf(model.getSequencia().getId().toString()));

		nffJson.setObservacao(model.getObservacao());

		nffJson.setIncoterms("0");

		nffJson.setUObservacao(model.getUObservacao());

		if (!TSUtil.isEmpty(model.getUTermo())) {

			nffJson.setUTermo(model.getUTermo());

		}

		// linhas do titulo

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<AssinaturaNotaFiscalSaidaLinhaModel>());

		}

		AssinaturaNotaFiscalSaidaLinhaModel linhaJson;
		for (AssinaturaNotaFiscalSaidaLinha linha : model.getLinhas()) {

			linhaJson = new AssinaturaNotaFiscalSaidaLinhaModel();

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

			nffJson.getLinhas().add(linhaJson);

		}

		nffJson = this.inserir(nffJson, this.conexaoSessaoHanaModel);

		model.setId(nffJson.getId());

		return model;

	}

	private AssinaturaNotaFiscalSaidaModel inserir(AssinaturaNotaFiscalSaidaModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		//System.out.println(new Gson().toJson(model));

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/Invoices").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(new Gson().toJson(model), MediaType.APPLICATION_JSON_TYPE));

		AssinaturaNotaFiscalSaidaModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			//System.out.println(json);

			resposta = new Gson().fromJson(json, AssinaturaNotaFiscalSaidaModel.class);

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
