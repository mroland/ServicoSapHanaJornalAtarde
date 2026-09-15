package br.com.atarde.servicosaphana.sap.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.atarde.servicosaphana.model.AssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaLinha;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaParcelaPedidoVendaModel;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaPedidoVendaLinhaModel;
import br.com.atarde.servicosaphana.sap.hana.model.AssinaturaPedidoVendaModel;
import br.com.atarde.servicosaphana.sap.hana.model.ConexaoSessaoHanaModel;
import br.com.atarde.servicosaphana.sap.hana.model.RetornoSapErroModel;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.atarde.servicosaphana.util.ConexaoSapUtil;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class AssinaturaPedidoVendaSapBusinessService {

	private Empresa empresa;
	private ConexaoSessaoHanaModel conexaoSessaoHanaModel;

	public AssinaturaPedidoVenda inserir(AssinaturaPedidoVenda model) throws Exception {

		this.initObjetosNaRequisicao(model.getEmpresa());

		AssinaturaPedidoVendaModel nffJson = new AssinaturaPedidoVendaModel();

		nffJson.setParceiroNegocioId(model.getCliente().getId());

		nffJson.setIdExterno(model.getIdExterno());

		nffJson.setFilialId(model.getFilial().getId());

		nffJson.setDataDocumento(TSParseUtil.dateToString(model.getDataDocumento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

		// e setar sempre a data de vencimento(data de entrega) a data de documento
		nffJson.setDataVencimento(nffJson.getDataDocumento());

		if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {

			// pegar a condicao de pagamento
			nffJson.setCondicaoPagamentoId(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));

			if (!TSUtil.isEmpty(model.getParcelas())) {

				if (TSUtil.isEmpty(nffJson.getParcelas())) {

					nffJson.setParcelas(new ArrayList<AssinaturaParcelaPedidoVendaModel>());

				}

				StringBuilder vencimentos = new StringBuilder();
				AssinaturaParcelaPedidoVendaModel parcelaJsonModel;
				for (ParcelaAB parcela : model.getParcelas()) {

					// verificar se vai add o InstallmentId 0,1,2

					parcelaJsonModel = new AssinaturaParcelaPedidoVendaModel();

					parcelaJsonModel.setDataVencimento(TSParseUtil.dateToString(parcela.getDataVencimento(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

					parcelaJsonModel.setValor(parcela.getValor().doubleValue());

					if (parcela.getValor().compareTo(BigDecimal.ZERO) == 0) {
						parcelaJsonModel.setPercentual(100D);
					}

					nffJson.getParcelas().add(parcelaJsonModel);

					// pega a ultima data de vencimento
					vencimentos.append(TSParseUtil.dateToString(parcela.getDataVencimento(), TSDateUtil.DD_MM_YYYY)).append(" | ");

				}

				nffJson.setObservacao("Vencimento: " + vencimentos.toString());

			}

		} else {
			
			nffJson.setObservacao("Vencimento: " + TSParseUtil.dateToString(model.getDataVencimento(), TSDateUtil.DD_MM_YYYY));

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

		nffJson.setIncoterms("0");

		nffJson.setUObservacao(model.getUObservacao());

		if (!TSUtil.isEmpty(model.getUTermo())) {

			nffJson.setUTermo(model.getUTermo());

		}

		// linhas do titulo

		if (TSUtil.isEmpty(nffJson.getLinhas())) {

			nffJson.setLinhas(new ArrayList<AssinaturaPedidoVendaLinhaModel>());

		}

		AssinaturaPedidoVendaLinhaModel linhaJson;
		for (AssinaturaPedidoVendaLinha linha : model.getLinhas()) {

			linhaJson = new AssinaturaPedidoVendaLinhaModel();

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

		model.setArquivoRemessaSap(nffJson.getArquivoRemessaSap());

		return model;

	}

	private AssinaturaPedidoVendaModel inserir(AssinaturaPedidoVendaModel model, ConexaoSessaoHanaModel conexaoSessaoHanaModel) throws Exception {

		// System.out.println(new Gson().toJson(model));
		
		String arquivoRemessaSap = new Gson().toJson(model);

		Response response = Utilitarios.createClient().target(Utilitarios.getUrlAcesso(this.empresa.getUrlSapHana()) + "/Orders").request(MediaType.APPLICATION_JSON.concat("; charset=UTF-8")).header(HttpHeaders.COOKIE, "B1SESSION=" + conexaoSessaoHanaModel.getSessaoId()).post(Entity.entity(arquivoRemessaSap, MediaType.APPLICATION_JSON_TYPE));

		AssinaturaPedidoVendaModel resposta;

		if (response.getStatusInfo().getStatusCode() == 200 || response.getStatusInfo().getStatusCode() == 201) {

			String json = response.readEntity(String.class);

			// System.out.println(json);

			resposta = new Gson().fromJson(json, AssinaturaPedidoVendaModel.class);
			
			resposta.setArquivoRemessaSap(arquivoRemessaSap);

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
