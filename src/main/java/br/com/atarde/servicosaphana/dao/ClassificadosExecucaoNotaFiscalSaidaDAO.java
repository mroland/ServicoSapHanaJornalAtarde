/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ClassificadosExecucaoNotaFiscalSaidaDAO {

	public ClassificadosExecucaoNotaFiscalSaida inserirInterface(ClassificadosExecucaoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("classificadosExecucaonotafiscalsaida_id_seq"));

		broker.setSQL("INSERT INTO CLASSIFICADOSExecucaoNOTAFISCALSAIDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, ANUNCIANTE_ID, ANUNCIANTE_TIPO, ANUNCIANTE_TIPO_IDENTIFICADOR, ANUNCIANTE_IDENTIFICADOR, ANUNCIANTE_NOME, ANUNCIANTE_NOME_FANTASIA, ANUNCIANTE_TELEFONE_RESIDENCIAL, ANUNCIANTE_TELEFONE_CELULAR, ANUNCIANTE_FAX, ANUNCIANTE_EMAIL, ANUNCIANTE_OBSERVACAO, ANUNCIANTE_ENDERECO_LOGRADOURO, ANUNCIANTE_ENDERECO_NUMERO, ANUNCIANTE_ENDERECO_COMPLEMENTO, ANUNCIANTE_ENDERECO_BAIRRO, ANUNCIANTE_ENDERECO_CIDADE, ANUNCIANTE_ENDERECO_ESTADO, ANUNCIANTE_ENDERECO_CEP, ANUNCIANTE_ENDERECO_PAIS, ANUNCIANTE_ENDERECO_MUNICIPIO, ANUNCIANTE_INSCRICAO_ESTADUAL, ANUNCIANTE_INSCRICAO_ESTADUAL_SUBTRIB, ANUNCIANTE_INSCRICAO_MUNICIPAL, ANUNCIANTE_INSCRICAO_INSS, ANUNCIANTE_DATA_ATUALIZACAO, ANUNCIANTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, U_COMISSAO_AGENCIA, U_DIAS_PUBLICACAO, U_DATA_PUBLICACAO_FINAL, U_TITULO_PUBLICACAO, U_NUMERO_PI, U_TIPO_TRANSACAO, U_PERIODO, U_FORMATO, U_PAGEVIEWS, U_ENTREGA_VENDEDOR, U_PRODUTO, U_CAMPANHA, U_POSTO_ID, U_DATA_PUBLICACAO_INICIAL, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO, ANUNCIANTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, ANUNCIANTE_ENDERECO_ENTREGA_DEFAULT, ANUNCIANTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, ANUNCIANTE_ENDERECO_TIPO_LOGRADOURO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(),

				model.getCliente().getId(), model.getCliente().getTipo(), model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(),

				model.getAnunciante().getId(), model.getAnunciante().getTipo(), model.getAnunciante().getIdentificadorFiscal().getTipoIdentificador(), model.getAnunciante().getIdentificadorFiscal().getIdentificador(), model.getAnunciante().getNome(), model.getAnunciante().getNomeFantasia(), model.getAnunciante().getTelefoneResidencial(), model.getAnunciante().getTelefoneCelular(), model.getAnunciante().getFax(), model.getAnunciante().getEmail(), model.getAnunciante().getObservacao(), model.getAnunciante().getEndereco().getLogradouro(), model.getAnunciante().getEndereco().getNumero(), model.getAnunciante().getEndereco().getComplemento(), model.getAnunciante().getEndereco().getBairro(), model.getAnunciante().getEndereco().getCidade(), model.getAnunciante().getEndereco().getEstado().getId(), model.getAnunciante().getEndereco().getCep(), model.getAnunciante().getEndereco().getPais().getId(), model.getAnunciante().getEndereco().getMunicipio().getId(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getAnunciante().getIdentificadorFiscal().getInscricaoMunicipal(), model.getAnunciante().getIdentificadorFiscal().getInscricaoINSS(), model.getAnunciante().getDataAtualizacao(), model.getAnunciante().getClassificacao().getId(),

				model.getVendedor().getId(), model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getUComissaoAgencia(), model.getUDiasPublicacao(), model.getUDataPublicacaoFinal(), model.getUTituloPublicacao(), model.getUNumeroPI(), model.getUTipoTransacao(), model.getUPeriodo(), model.getUFormato(), model.getUPageViews(), model.getUEntregaVendedor(), model.getUProduto(), model.getUCampanha(), model.getUPostoId(), model.getUDataPublicacaoInicial(), model.getPedidoVenda().getId(),

				model.getCliente().getFlagEndereco(), model.getAnunciante().getFlagEndereco(),

				model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),

				model.getAnunciante().getEnderecoEntregaDefault(), model.getAnunciante().getEnderecoCobrancaDefault(),

				model.getCliente().getEndereco().getTipoLogradouro(), model.getAnunciante().getEndereco().getTipoLogradouro());

		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			ClassificadosExecucaoNotaFiscalSaidaLinha linha = (ClassificadosExecucaoNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new ClassificadosExecucaoNotaFiscalSaida("interfaceId", model.getInterfaceId()));

			new ClassificadosExecucaoNotaFiscalSaidaLinhaDAO().inserirInterface(linha, broker);

		}

		broker.endTransaction();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<ClassificadosExecucaoNotaFiscalSaida> pesquisarInterface(ClassificadosExecucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID , CLIENTE_COBRANCA_ID , 'C' AS TIPO, CLIENTE_COBRANCA_TIPO_IDENTIFICADOR , CLIENTE_COBRANCA_IDENTIFICADOR , CLIENTE_COBRANCA_NOME , CLIENTE_COBRANCA_NOME_FANTASIA , CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL , CLIENTE_COBRANCA_TELEFONE_CELULAR , CLIENTE_COBRANCA_FAX , CLIENTE_COBRANCA_EMAIL , CLIENTE_COBRANCA_OBSERVACAO , CLIENTE_COBRANCA_ENDERECO_LOGRADOURO , CLIENTE_COBRANCA_ENDERECO_NUMERO , CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO , CLIENTE_COBRANCA_ENDERECO_BAIRRO , CLIENTE_COBRANCA_ENDERECO_CIDADE , CLIENTE_COBRANCA_ENDERECO_ESTADO , CLIENTE_COBRANCA_ENDERECO_CEP , CLIENTE_COBRANCA_ENDERECO_PAIS , CLIENTE_COBRANCA_ENDERECO_MUNICIPIO , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB , CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL , CLIENTE_COBRANCA_INSCRICAO_INSS , CLIENTE_COBRANCA_DATA_ATUALIZACAO , CLIENTE_COBRANCA_CLASSIFICACAO_ID , VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR , VENDEDOR_IDENTIFICADOR , VENDEDOR_NOME , VENDEDOR_DATA_ATUALIZACAO , VENDEDOR_GRUPO_COMISSAO_ID , U_VENDEDOR_CGA , ID_EXTERNO , ORIGEM_ID , DATA_LANCAMENTO , DATA_DOCUMENTO , DATA_VENCIMENTO , CONDICAO_PAGAMENTO , SEQUENCIA , VALOR , DATA_CRIACAO , DATA_ATUALIZACAO , CRIADO_POR , ATUALIZADO_POR , DATA_EXPORTACAO , U_VALOR_BRUTO , U_ENDERECO_ENTREGA , STATUS_ID , DATA_IMPORTACAO , MENSAGEM_ERRO , EMPRESA_ID , FILIAL_ID , CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT , CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_COBRANCA_FLAG_ENDERECO , CASE POSITION('_' IN ID_EXTERNO) WHEN 0 THEN ID_EXTERNO ELSE SUBSTRING(ID_EXTERNO, 1, POSITION('_' IN ID_EXTERNO) - 1) END AS AUTORIZACAO_PUBLICIDADE FROM CLASSIFICADOS_EXECUCAO_NFF_SAIDA WHERE STATUS_ID !=2 AND EMPRESA_ID = ? ORDER BY ID LIMIT 200 ", model.getEmpresa().getId());

		return broker.getCollectionBean(ClassificadosExecucaoNotaFiscalSaida.class, "interfaceId",

				"cliente.id", "cliente.tipo", "cliente.identificadorFiscal.tipoIdentificador", "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro", "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade", "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id",

				"vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome", "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga",

				"idExterno", "origem.id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "sequencia.id", "valor", "dataCriacao", "dataAtualizacao", "criadoPor", "atualizadoPor", "dataExportacao", "uValorBruto", "uEnderecoEntrega", "status.id", "dataImportacao", "mensagemErro", "empresa.id", "filial.id", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault", "cliente.flagEndereco", "uAutorizacaoPublicidade");
	}

	public void alterarInterface(ClassificadosExecucaoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE CLASSIFICADOS_EXECUCAO_NFF_SAIDA SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(ClassificadosExecucaoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CLASSIFICADOS_EXECUCAO_NFF_SAIDA WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ClassificadosExecucaoNotaFiscalSaida> pesquisarPorAtrasadaInterface(ClassificadosExecucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM CLASSIFICADOS_EXECUCAO_NFF_SAIDA WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(ClassificadosExecucaoNotaFiscalSaida.class, "interfaceId");

	}

	public ClassificadosExecucaoNotaFiscalSaida obterIdExternoInterface(ClassificadosExecucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM CLASSIFICADOS_EXECUCAO_NFF_SAIDA WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (ClassificadosExecucaoNotaFiscalSaida) broker.getObjectBean(ClassificadosExecucaoNotaFiscalSaida.class, "interfaceId");
	}
}
