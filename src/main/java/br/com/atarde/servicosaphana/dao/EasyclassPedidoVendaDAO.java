/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVendaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;


/**
 * 
 * @author mroland
 */
public class EasyclassPedidoVendaDAO {

	public EasyclassPedidoVenda inserirInterface(EasyclassPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("easyclasspedidovenda_id_seq"));

		broker.setSQL("INSERT INTO EASYCLASS_PEDIDOVENDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, ANUNCIANTE_ID, ANUNCIANTE_TIPO, ANUNCIANTE_TIPO_IDENTIFICADOR, ANUNCIANTE_IDENTIFICADOR, ANUNCIANTE_NOME, ANUNCIANTE_NOME_FANTASIA, ANUNCIANTE_TELEFONE_RESIDENCIAL, ANUNCIANTE_TELEFONE_CELULAR, ANUNCIANTE_FAX, ANUNCIANTE_EMAIL, ANUNCIANTE_OBSERVACAO, ANUNCIANTE_ENDERECO_LOGRADOURO, ANUNCIANTE_ENDERECO_NUMERO, ANUNCIANTE_ENDERECO_COMPLEMENTO, ANUNCIANTE_ENDERECO_BAIRRO, ANUNCIANTE_ENDERECO_CIDADE, ANUNCIANTE_ENDERECO_ESTADO, ANUNCIANTE_ENDERECO_CEP, ANUNCIANTE_ENDERECO_PAIS, ANUNCIANTE_ENDERECO_MUNICIPIO, ANUNCIANTE_INSCRICAO_ESTADUAL, ANUNCIANTE_INSCRICAO_ESTADUAL_SUBTRIB, ANUNCIANTE_INSCRICAO_MUNICIPAL, ANUNCIANTE_INSCRICAO_INSS, ANUNCIANTE_DATA_ATUALIZACAO, ANUNCIANTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_PEDIDOVENDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, U_COMISSAO_AGENCIA, U_DIAS_PUBLICACAO, U_DATA_PUBLICACAO_FINAL, U_TITULO_PUBLICACAO, U_NUMERO_PI, U_TIPO_TRANSACAO, U_PERIODO, U_FORMATO, U_PAGEVIEWS, U_ENTREGA_VENDEDOR, U_PRODUTO, U_CAMPANHA, U_POSTO_ID, U_DATA_PUBLICACAO_INICIAL, CLIENTE_FLAG_ENDERECO, ANUNCIANTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, ANUNCIANTE_ENDERECO_ENTREGA_DEFAULT, ANUNCIANTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, ANUNCIANTE_ENDERECO_TIPO_LOGRADOURO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(),
				
				model.getCliente().getId(), model.getCliente().getTipo(), 
				model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), 
				model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), 
				model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), 
				model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), 
				model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), 
				model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), 
				
				model.getAnunciante().getId(), model.getAnunciante().getTipo(), 
				model.getAnunciante().getIdentificadorFiscal().getTipoIdentificador(), model.getAnunciante().getIdentificadorFiscal().getIdentificador(), 
				model.getAnunciante().getNome(), model.getAnunciante().getNomeFantasia(), model.getAnunciante().getTelefoneResidencial(), 
				model.getAnunciante().getTelefoneCelular(), model.getAnunciante().getFax(), model.getAnunciante().getEmail(), model.getAnunciante().getObservacao(), 
				model.getAnunciante().getEndereco().getLogradouro(), model.getAnunciante().getEndereco().getNumero(), model.getAnunciante().getEndereco().getComplemento(), 
				model.getAnunciante().getEndereco().getBairro(), model.getAnunciante().getEndereco().getCidade(), model.getAnunciante().getEndereco().getEstado().getId(), 
				model.getAnunciante().getEndereco().getCep(), model.getAnunciante().getEndereco().getPais().getId(), model.getAnunciante().getEndereco().getMunicipio().getId(), 
				model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getAnunciante().getIdentificadorFiscal().getInscricaoMunicipal(), model.getAnunciante().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getAnunciante().getDataAtualizacao(), model.getAnunciante().getClassificacao().getId(), 				
							
				model.getVendedor().getId(), 
				model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), 
				model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), 
				model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), 
				model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), 
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getUComissaoAgencia(), model.getUDiasPublicacao(),
				model.getUDataPublicacaoFinal(), model.getUTituloPublicacao(), model.getUNumeroPI(), model.getUTipoTransacao(), model.getUPeriodo(), model.getUFormato(),
				model.getUPageViews(), model.getUEntregaVendedor(), model.getUProduto(), model.getUCampanha(), model.getUPostoId(), model.getUDataPublicacaoInicial(),		
				
				model.getCliente().getFlagEndereco(), model.getAnunciante().getFlagEndereco(),
				
				model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				
				model.getAnunciante().getEnderecoEntregaDefault(), model.getAnunciante().getEnderecoCobrancaDefault(),
				
				model.getCliente().getEndereco().getTipoLogradouro(), model.getAnunciante().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (EasyclassPedidoVendaLinha item : model.getLinhas()) {

			EasyclassPedidoVendaLinha linha = (EasyclassPedidoVendaLinha) item;

			linha.setPedidoVenda(new EasyclassPedidoVenda("interfaceId",model.getInterfaceId()));
			
			new EasyclassPedidoVendaLinhaDAO().inserirInterface(linha,broker);

		}

		broker.endTransaction();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<EasyclassPedidoVenda> pesquisarInterface(EasyclassPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT TOP 100 ID,CLIENTE_TIPO_IDENTIFICADOR,CLIENTE_IDENTIFICADOR,CLIENTE_NOME,CLIENTE_NOME_FANTASIA,CLIENTE_TELEFONE_RESIDENCIAL,CLIENTE_TELEFONE_CELULAR,CLIENTE_FAX,CLIENTE_EMAIL,CLIENTE_OBSERVACAO,CLIENTE_ENDERECO_LOGRADOURO,CLIENTE_ENDERECO_NUMERO,CLIENTE_ENDERECO_COMPLEMENTO,CLIENTE_ENDERECO_BAIRRO,CLIENTE_ENDERECO_CIDADE,CLIENTE_ENDERECO_ESTADO,CLIENTE_ENDERECO_CEP,CLIENTE_ENDERECO_PAIS,CLIENTE_ENDERECO_MUNICIPIO,CLIENTE_INSCRICAO_ESTADUAL,CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_INSCRICAO_MUNICIPAL,CLIENTE_INSCRICAO_INSS,CLIENTE_DATA_ATUALIZACAO,CLIENTE_CLASSIFICACAO_ID,CLIENTE_COBRANCA_TIPO_IDENTIFICADOR,CLIENTE_COBRANCA_IDENTIFICADOR,CLIENTE_COBRANCA_NOME,CLIENTE_COBRANCA_NOME_FANTASIA,CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL,CLIENTE_COBRANCA_TELEFONE_CELULAR,CLIENTE_COBRANCA_FAX,CLIENTE_COBRANCA_EMAIL,CLIENTE_COBRANCA_OBSERVACAO,CLIENTE_COBRANCA_ENDERECO_LOGRADOURO,CLIENTE_COBRANCA_ENDERECO_NUMERO,CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO,CLIENTE_COBRANCA_ENDERECO_BAIRRO,CLIENTE_COBRANCA_ENDERECO_CIDADE,CLIENTE_COBRANCA_ENDERECO_ESTADO,CLIENTE_COBRANCA_ENDERECO_CEP,CLIENTE_COBRANCA_ENDERECO_PAIS,CLIENTE_COBRANCA_ENDERECO_MUNICIPIO,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL,CLIENTE_COBRANCA_INSCRICAO_INSS,CLIENTE_COBRANCA_DATA_ATUALIZACAO,CLIENTE_COBRANCA_CLASSIFICACAO_ID,VENDEDOR_TIPO_IDENTIFICADOR,VENDEDOR_IDENTIFICADOR,VENDEDOR_NOME,VENDEDOR_DATA_ATUALIZACAO,VENDEDOR_GRUPO_COMISSAO_ID,ID_EXTERNO,ORIGEM_ID,DATA_LANCAMENTO,DATA_DOCUMENTO,DATA_VENCIMENTO,CONDICAO_PAGAMENTO,SEQUENCIA,VALOR_LIQUIDO,DATA_CRIACAO,DATA_ATUALIZACAO,CRIADO_POR,ATUALIZADO_POR,DATA_EXPORTACAO,U_VALOR_BRUTO,U_COMISSAO_AGENCIA,U_DIAS_PUBLICACAO,U_DATA_PUBLICACAO_FINAL,U_ENDERECO_ENTREGA, U_TITULO_PUBLICACAO, STATUS_ID, DATA_IMPORTACAO, MENSAGEM_ERRO_IMPORTACAO, CASE CHARINDEX('_', ID_EXTERNO) WHEN 0 THEN ID_EXTERNO ELSE SUBSTRING(ID_EXTERNO, 1, CHARINDEX('_', ID_EXTERNO) - 1) END AS UAUTORIZACAOPUBLICIDADE, U_NUMERO_PI, U_TIPO_TRANSACAO, U_PERIODO, U_PAGEVIEWS, U_FORMATO, U_ENTREGA_VENDEDOR, U_PRODUTO, U_CAMPANHA, U_POSTO_ID, U_DATA_PUBLICACAO_INICIAL, U_VENDEDOR_CGA, U_OBSERVACAO, U_PERMUTA FROM INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA WITH (NOLOCK) WHERE ISNULL(STATUS_ID,9) = ISNULL(?,ISNULL(STATUS_ID,9)) AND STATUS_ID <> 2 AND EMPRESA_ID = ? ORDER BY ID", model.getEmpresa().getId());
		
		return broker.getCollectionBean(EasyclassPedidoVenda.class, "interfaceId", 
				"cliente.identificadorFiscal.tipoIdentificador", "cliente.identificadorFiscal.identificador",
                "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial", "cliente.telefoneCelular",
                "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro",
                "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro",
                "cliente.endereco.cidade", "cliente.endereco.estado", "cliente.endereco.cep", "cliente.endereco.pais",
                "cliente.endereco.municipio.id", "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
                "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS",
                "cliente.dataAtualizacao", "cliente.classificacao.id",
                
                "anunciante.identificadorFiscal.tipoIdentificador", "anunciante.identificadorFiscal.identificador",
                "anunciante.nome", "anunciante.nomeFantasia", "anunciante.telefoneResidencial", "anunciante.telefoneCelular",
                "anunciante.fax", "anunciante.email", "anunciante.observacao", "anunciante.endereco.logradouro",
                "anunciante.endereco.numero", "anunciante.endereco.complemento", "anunciante.endereco.bairro",
                "anunciante.endereco.cidade", "anunciante.endereco.estado", "anunciante.endereco.cep", "anunciante.endereco.pais",
                "anunciante.endereco.municipio.id", "anunciante.identificadorFiscal.inscricaoEstadual", "anunciante.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
                "anunciante.identificadorFiscal.inscricaoMunicipal", "anunciante.identificadorFiscal.inscricaoINSS",
                "anunciante.dataAtualizacao", "anunciante.classificacao.id",
                
                "vendedor.identificadorFiscal.tipoIdentificador", "vendedor.identificadorFiscal.identificador", "vendedor.nome",
                "vendedor.dataAtualizacao", "vendedor.grupoComissaoModel.id",
                
                "idExterno", "origem.id", "dataLancamento", "dataDocumento", "dataVencimento", "condicaoPagamento.id", "sequencia.id", "valorLiquido", "dataCriacao",
                "dataAtualizacao", "criadoPor", "atualizadoPor", "dataExportacao", "uValorBruto", "uComissaoAgencia", "uDiasPublicacao", "uDataPublicacaoFinal", "uEnderecoEntrega",
                "uTituloPublicacao", "status", "dataImportacao", "mensagemErroImportacao", "uAutorizacaoPublicidade", "uNumeroPI","uTipoTransacao", "uPeriodo", "uPageViews", 
                "uFormato", "uEntregaVendedor","uProduto", "uCampanha", "uPostoId", "uDataPublicacaoInicial", "vendedor.uCga", "uObservacao", "uPermuta");
	}

	public void alterarInterface(EasyclassPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA SET STATUS_ID = ?, MENSAGEM_ERRO_IMPORTACAO = ?, DATA_ATUALIZACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), new Timestamp(model.getDataAtualizacao().getTime()), model.getInterfaceId());
		
		broker.execute();
		
	}

	public void excluirInterface(EasyclassPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("DELETE FROM INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA WHERE ID = ?", model.getInterfaceId());
		
		broker.execute();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EasyclassPedidoVenda> pesquisarPorAtrasadaInterface(EasyclassPedidoVenda model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
        
        broker.setSQL("SELECT ID FROM INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA A WITH(NOLOCK) WHERE A.STATUS_ID = ? AND DATEDIFF(minute,A.DATA_ATUALIZACAO, GETDATE()) >=30", model.getStatus().getId());
        
        return broker.getCollectionBean(EasyclassPedidoVenda.class, "interfaceId");
        
	}

	
	public EasyclassPedidoVenda obterIdExternoInterfaceIdExternoInterface(EasyclassPedidoVenda model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID FROM INTEGRACAOSISTEMALEGADO.DBO.EASYCLASS_PEDIDOVENDA A WITH(NOLOCK) WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno());
		
		return (EasyclassPedidoVenda) broker.getObjectBean(EasyclassPedidoVenda.class, "interfaceId");
	}	
}
