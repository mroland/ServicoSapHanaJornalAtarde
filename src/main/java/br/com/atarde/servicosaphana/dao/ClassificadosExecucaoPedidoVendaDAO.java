/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ClassificadosExecucaoPedidoVendaDAO {

	@SuppressWarnings("unchecked")
	public List<ClassificadosExecucaoPedidoVenda> pesquisarInterface(ClassificadosExecucaoPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID , CLIENTE_COBRANCA_ID , 'C' AS TIPO, CLIENTE_COBRANCA_TIPO_IDENTIFICADOR , CLIENTE_COBRANCA_IDENTIFICADOR , CLIENTE_COBRANCA_NOME , CLIENTE_COBRANCA_NOME_FANTASIA , CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL , CLIENTE_COBRANCA_TELEFONE_CELULAR , CLIENTE_COBRANCA_FAX , CLIENTE_COBRANCA_EMAIL , CLIENTE_COBRANCA_OBSERVACAO , CLIENTE_COBRANCA_ENDERECO_LOGRADOURO , CLIENTE_COBRANCA_ENDERECO_NUMERO , CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO , CLIENTE_COBRANCA_ENDERECO_BAIRRO , CLIENTE_COBRANCA_ENDERECO_CIDADE , CLIENTE_COBRANCA_ENDERECO_ESTADO , CLIENTE_COBRANCA_ENDERECO_CEP , CLIENTE_COBRANCA_ENDERECO_PAIS , CLIENTE_COBRANCA_ENDERECO_MUNICIPIO , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB , CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL , CLIENTE_COBRANCA_INSCRICAO_INSS , CLIENTE_COBRANCA_DATA_ATUALIZACAO , CLIENTE_COBRANCA_CLASSIFICACAO_ID , VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR , VENDEDOR_IDENTIFICADOR , VENDEDOR_NOME , VENDEDOR_DATA_ATUALIZACAO , VENDEDOR_GRUPO_COMISSAO_ID , U_VENDEDOR_CGA , ID_EXTERNO , ORIGEM_ID , DATA_LANCAMENTO , DATA_DOCUMENTO , DATA_VENCIMENTO , CONDICAO_PAGAMENTO , SEQUENCIA , VALOR , DATA_CRIACAO , DATA_ATUALIZACAO , CRIADO_POR , ATUALIZADO_POR , DATA_EXPORTACAO , U_VALOR_BRUTO , U_ENDERECO_ENTREGA , STATUS_ID , DATA_IMPORTACAO , MENSAGEM_ERRO , EMPRESA_ID , FILIAL_ID , CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT , CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_COBRANCA_FLAG_ENDERECO , CASE POSITION('_' IN ID_EXTERNO) WHEN 0 THEN ID_EXTERNO ELSE SUBSTRING(ID_EXTERNO, 1, POSITION('_' IN ID_EXTERNO) - 1) END AS AUTORIZACAO_PUBLICIDADE, ARQUIVO_REMESSA FROM CLASSIFICADOS_EXECUCAO_PEDIDOVENDA WHERE STATUS_ID !=2 AND EMPRESA_ID = ? ORDER BY ID LIMIT 50 ", model.getEmpresa().getId());

		return broker.getCollectionBean(ClassificadosExecucaoPedidoVenda.class, "interfaceId",

				"cliente.id", "cliente.tipo", "cliente.identificadorFiscal.tipoIdentificador", "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial", "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro", "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade", "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria", "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao", "cliente.classificacao.id",

				"vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome", "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga",

				"idExterno", "origem.id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "sequencia.id", "valor", "dataCriacao", "dataAtualizacao", "criadoPor", "atualizadoPor", "dataExportacao", "uValorBruto", "uEnderecoEntrega", "status.id", "dataImportacao", "mensagemErro", "empresa.id", "filial.id", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault", "cliente.flagEndereco", "uAutorizacaoPublicidade", "arquivoRemessa");
	}

	public void alterarInterface(ClassificadosExecucaoPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE CLASSIFICADOS_EXECUCAO_PEDIDOVENDA SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), TSUtil.isEmpty(model.getDataAtualizacao()) ? null : new Timestamp(model.getDataAtualizacao().getTime()), TSUtil.isEmpty(model.getDataImportacao()) ? null : new Timestamp(model.getDataImportacao().getTime()), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(ClassificadosExecucaoPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CLASSIFICADOS_EXECUCAO_PEDIDOVENDA WHERE ID = ?", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ClassificadosExecucaoPedidoVenda> pesquisarPorAtrasadaInterface(ClassificadosExecucaoPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM CLASSIFICADOS_EXECUCAO_PEDIDOVENDA WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());

		return broker.getCollectionBean(ClassificadosExecucaoPedidoVenda.class, "interfaceId");

	}

	public ClassificadosExecucaoPedidoVenda obterIdExternoInterface(ClassificadosExecucaoPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM CLASSIFICADOS_EXECUCAO_PEDIDOVENDA WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (ClassificadosExecucaoPedidoVenda) broker.getObjectBean(ClassificadosExecucaoPedidoVenda.class, "interfaceId");
	}
}
