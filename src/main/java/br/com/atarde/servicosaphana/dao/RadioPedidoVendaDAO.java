/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.RadioPedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class RadioPedidoVendaDAO {

	@SuppressWarnings("unchecked")
	public List<RadioPedidoVenda> pesquisarInterface(RadioPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, 'C' AS TIPO, CLIENTE_ID, CLIENTE_TIPO_IDENTIFICADOR,CLIENTE_IDENTIFICADOR,CLIENTE_NOME,CLIENTE_NOME_FANTASIA,CLIENTE_TELEFONE_RESIDENCIAL,CLIENTE_TELEFONE_CELULAR,CLIENTE_FAX,CLIENTE_EMAIL,CLIENTE_OBSERVACAO,CLIENTE_ENDERECO_LOGRADOURO,CLIENTE_ENDERECO_NUMERO,CLIENTE_ENDERECO_COMPLEMENTO,CLIENTE_ENDERECO_BAIRRO,CLIENTE_ENDERECO_CIDADE,CLIENTE_ENDERECO_ESTADO,CLIENTE_ENDERECO_CEP,CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL,CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_INSCRICAO_MUNICIPAL,CLIENTE_INSCRICAO_INSS,CLIENTE_DATA_ATUALIZACAO,CLIENTE_CLASSIFICACAO_ID, 'C' AS TIPO, CLIENTE_COBRANCA_ID, CLIENTE_COBRANCA_TIPO_IDENTIFICADOR,CLIENTE_COBRANCA_IDENTIFICADOR,CLIENTE_COBRANCA_NOME,CLIENTE_COBRANCA_NOME_FANTASIA,CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL,CLIENTE_COBRANCA_TELEFONE_CELULAR,CLIENTE_COBRANCA_FAX,CLIENTE_COBRANCA_EMAIL,CLIENTE_COBRANCA_OBSERVACAO,CLIENTE_COBRANCA_ENDERECO_LOGRADOURO,CLIENTE_COBRANCA_ENDERECO_NUMERO,CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO,CLIENTE_COBRANCA_ENDERECO_BAIRRO,CLIENTE_COBRANCA_ENDERECO_CIDADE,CLIENTE_COBRANCA_ENDERECO_ESTADO,CLIENTE_COBRANCA_ENDERECO_CEP,CLIENTE_COBRANCA_ENDERECO_PAIS, CLIENTE_COBRANCA_ENDERECO_MUNICIPIO,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL,CLIENTE_COBRANCA_INSCRICAO_INSS,CLIENTE_COBRANCA_DATA_ATUALIZACAO,CLIENTE_COBRANCA_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR,VENDEDOR_IDENTIFICADOR,VENDEDOR_NOME,VENDEDOR_DATA_ATUALIZACAO,VENDEDOR_GRUPO_COMISSAO_ID, U_VENDEDOR_CGA, ID_EXTERNO,ORIGEM_ID,DATA_LANCAMENTO,DATA_DOCUMENTO,DATA_VENCIMENTO,CONDICAO_PAGAMENTO, SEQUENCIA, VALOR, DATA_CRIACAO,DATA_ATUALIZACAO,CRIADO_POR,ATUALIZADO_POR,DATA_EXPORTACAO, U_VALOR_BRUTO,U_COMISSAO_AGENCIA, U_ENDERECO_ENTREGA, STATUS_ID, DATA_IMPORTACAO, MENSAGEM_ERRO, U_NUMERO_PI, U_DATA_CONTRATO, U_PERIODO_VEICULACAO, U_TIPO_TRANSACAO, U_ENTREGA_VENDEDOR, U_POSTO_ID, U_DATA_PUBLICACAO_INICIAL, U_OBSERVACAO, U_PERMUTA, EMPRESA_ID, CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT, CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_COBRANCA_FLAG_ENDERECO, CLIENTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, FILIAL_ID, CASE POSITION('_' IN ID_EXTERNO) WHEN 0 THEN ID_EXTERNO ELSE SUBSTRING(ID_EXTERNO, 1, POSITION('_' IN ID_EXTERNO) - 1) END AS AUTORIZACAO_PUBLICIDADE, ARQUIVO_REMESSA, U_INTERMEDIADOR FROM RADIO_PEDIDOVENDA WHERE STATUS_ID !=2 AND EMPRESA_ID = ? ORDER BY ID LIMIT 50 ", model.getEmpresa().getId());

		return broker.getCollectionBean(RadioPedidoVenda.class, "interfaceId",

				"anunciante.tipo", "anunciante.id", "anunciante.identificadorFiscal.tipoIdentificador", "anunciante.identificadorFiscal.identificador", "anunciante.nome", "anunciante.nomeFantasia", "anunciante.telefoneResidencial", "anunciante.telefoneCelular", "anunciante.fax", "anunciante.email", "anunciante.observacao", "anunciante.endereco.logradouro", "anunciante.endereco.numero", "anunciante.endereco.complemento", "anunciante.endereco.bairro", "anunciante.endereco.cidade", "anunciante.endereco.estado.id", "anunciante.endereco.cep", "anunciante.endereco.pais.id", "anunciante.endereco.municipio.id", "anunciante.identificadorFiscal.inscricaoEstadual", "anunciante.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "anunciante.identificadorFiscal.inscricaoMunicipal", "anunciante.identificadorFiscal.inscricaoINSS", "anunciante.dataAtualizacao", "anunciante.classificacao.id",

				"cliente.tipo", "cliente.id", "cliente.identificadorFiscal.tipoIdentificador", "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro", "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade", "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id",

				"vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome", "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga",

				"idExterno", "origem.id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "sequencia.id", "valor", "dataCriacao", "dataAtualizacao", "criadoPor", "atualizadoPor", "dataExportacao", "uValorBruto", "uComissaoAgencia", "uEnderecoEntrega", "status", "dataImportacao", "mensagemErro", "uNumeroPI", "uDataContrato", "uPeriodoVeiculacao", "uTipoTransacao", "uEntregaVendedor", "uPostoId", "uDataPublicacaoInicial", "uObservacao", "uPermuta", "empresa.id", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault", "cliente.flagEndereco", "anunciante.flagEndereco", "anunciante.enderecoEntregaDefault", "anunciante.enderecoCobrancaDefault", "filial.id", "uAutorizacaoPublicidade", "arquivoRemessa", "uIntermediador");

	}

	public void alterarInterface(RadioPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE RADIO_PEDIDOVENDA SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(RadioPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM RADIO_PEDIDOVENDA WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<RadioPedidoVenda> pesquisarPorAtrasadaInterface(RadioPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM RADIO_PEDIDOVENDA A WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(RadioPedidoVenda.class, "interfaceId");

	}

	public RadioPedidoVenda obterIdExternoInterface(RadioPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM RADIO_PEDIDOVENDA A WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (RadioPedidoVenda) broker.getObjectBean(RadioPedidoVenda.class, "interfaceId");
	}
}
