/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
public class AssinaturaNotaFiscalSaidaDAO {

	public AssinaturaNotaFiscalSaidaDAO() {
	}

	public AssinaturaNotaFiscalSaida inserirInterface(AssinaturaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("assinaturanotafiscalsaida_id_seq"));

		broker.setSQL("INSERT INTO ASSINATURANOTAFISCALSAIDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_OBSERVACAO, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO,CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, U_TERMO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(),
				model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getId(), model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(),
				model.getEmpresa().getId(), model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getObservacao(), model.getUObservacao(), model.getPedidoVenda().getId(), model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(), model.getCliente().getEndereco().getTipoLogradouro(), model.getUTermo());

		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			AssinaturaNotaFiscalSaidaLinha linha = (AssinaturaNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new AssinaturaNotaFiscalSaida("interfaceId", model.getInterfaceId()));

			new AssinaturaNotaFiscalSaidaLinhaDAO().inserirInterface(linha, broker);

		}

		if (!TSUtil.isEmpty(model.getParcelas())) {

			for (ParcelaAB item : model.getParcelas()) {

				AssinaturaNotaFiscalSaidaParcela linha = (AssinaturaNotaFiscalSaidaParcela) item;

				linha.setNotaFiscalSaida(new AssinaturaNotaFiscalSaida("interfaceId", model.getInterfaceId()));

				new AssinaturaNotaFiscalSaidaParcelaDAO().inserirInterface(linha, broker);

			}

		}

		broker.endTransaction();

		return model;

	}

	@SuppressWarnings("unchecked")
	public List<AssinaturaNotaFiscalSaida> pesquisarInterface(AssinaturaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_OBSERVACAO, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, U_TERMO, FILIAL_ID FROM ASSINATURANOTAFISCALSAIDA WHERE EMPRESA_ID = ? AND STATUS_ID !=2 LIMIT 200", model.getEmpresa().getId());

		return broker.getCollectionBean(AssinaturaNotaFiscalSaida.class, "interfaceId", "cliente.id", "cliente.tipo", "cliente.identificadorFiscal.tipoIdentificador", "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro", "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade", "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id", "vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome",
				"vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga", "id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "dataExportacao", "dataImportacao", "dataAtualizacao", "sequencia.id", "status.id", "mensagemErro", "idExterno", "empresa.id", "origem.id", "uValorBruto", "valor", "uEnderecoEntrega", "observacao", "uObservacao", "pedidoVenda.id", "cliente.flagEndereco", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault", "cliente.endereco.tipoLogradouro", "uTermo", "filial.id");
	}

	public void alterarInterface(AssinaturaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE ASSINATURANOTAFISCALSAIDA SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), new Timestamp(model.getDataAtualizacao().getTime()), new Timestamp(model.getDataImportacao().getTime()), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(AssinaturaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM ASSINATURANOTAFISCALSAIDA WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<AssinaturaNotaFiscalSaida> pesquisarPorAtrasadaInterface(AssinaturaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM ASSINATURANOTAFISCALSAIDA WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(AssinaturaNotaFiscalSaida.class, "interfaceId");

	}

	public AssinaturaNotaFiscalSaida obterIdExternoInterface(AssinaturaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM ASSINATURANOTAFISCALSAIDA WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (AssinaturaNotaFiscalSaida) broker.getObjectBean(AssinaturaNotaFiscalSaida.class, "interfaceId");
	}
}
