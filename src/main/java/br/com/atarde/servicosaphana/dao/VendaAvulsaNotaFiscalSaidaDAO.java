/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class VendaAvulsaNotaFiscalSaidaDAO {

	public VendaAvulsaNotaFiscalSaidaDAO() {
	}

	public VendaAvulsaNotaFiscalSaida inserirInterface(VendaAvulsaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("vendaavulsanotafiscalsaida_id_seq"));

		broker.setSQL("INSERT INTO VENDAAVULSANOTAFISCALSAIDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_BANCA, U_LOTE, U_TIPO_BANCA, U_TIPO_FATURAMENTO, U_OBSERVACAO, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO,CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), 
				model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), 
				model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), 
				model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), 
				model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), 
				model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), 
				model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getId(), 
				model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), 
				model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), 
				model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), 
				model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), 
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getObservacao(), model.getUBanca(), 
				model.getULote(), model.getUTipoBanca(), model.getUTipoFaturamento(), model.getUObservacao(), model.getPedidoVenda().getId(),
				model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				model.getCliente().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			VendaAvulsaNotaFiscalSaidaLinha linha = (VendaAvulsaNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new VendaAvulsaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new VendaAvulsaNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}

		if (!TSUtil.isEmpty(model.getRomaneios()) && model.getRomaneios().size() != 0) {
			
			for (VendaAvulsaNotaFiscalSaidaRomaneio romaneio : model.getRomaneios()) {
				
				romaneio.setEmpresa(model.getEmpresa());
				
				romaneio.setNota(new VendaAvulsaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
				
				new VendaAvulsaNotaFiscalSaidaRomaneioDAO().inserirInterface(romaneio, broker);

			}

		}

		broker.endTransaction();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<VendaAvulsaNotaFiscalSaida> pesquisarInterface(VendaAvulsaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_BANCA, U_LOTE, U_TIPO_BANCA, U_TIPO_FATURAMENTO, U_OBSERVACAO, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, FILIAL_ID FROM VENDAAVULSANOTAFISCALSAIDA WHERE EMPRESA_ID = ? AND STATUS_ID !=2 LIMIT 200", model.getEmpresa().getId());
		
		return broker.getCollectionBean(VendaAvulsaNotaFiscalSaida.class, "interfaceId", "cliente.id", "cliente.tipo", "cliente.identificadorFiscal.tipoIdentificador",
				                             							  "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial",
				                             							  "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro",
				                             							  "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade",
				                             							  "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id",
				                             							  "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
				                             							  "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao",
				                             							  "cliente.classificacao.id", "vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome",
				                             							  "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga", "id", "dataLancamento", "dataDocumento",
				                             							  "dataVencimento", "condicaoPagamento.id", "dataExportacao", "dataImportacao", "dataAtualizacao", "sequencia.id",
				                             							  "status.id", "mensagemErro", "idExterno", "empresa.id", "origem.id", "uValorBruto", "valor", "uEnderecoEntrega",
				                             							  "observacao", "uBanca", "uLote", "uTipoBanca", "uTipoFaturamento", "uObservacao", "pedidoVenda.id", 
				                             							  "cliente.flagEndereco", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault", "cliente.endereco.tipoLogradouro", "filial.id");
	}

	public void alterarInterface(VendaAvulsaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE VENDAAVULSANOTAFISCALSAIDA SET STATUS_ID = ?, MENSAGEM_ERRO = ?, DATA_ATUALIZACAO =?, DATA_IMPORTACAO = ? WHERE ID = ?", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());
		
		broker.execute();
		
	}

	public void excluirInterface(VendaAvulsaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("DELETE FROM VENDAAVULSANOTAFISCALSAIDA WHERE ID = ?", model.getInterfaceId());
		
		broker.execute();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<VendaAvulsaNotaFiscalSaida> pesquisarPorAtrasadaInterface(VendaAvulsaNotaFiscalSaida model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
        
        broker.setSQL("SELECT ID FROM VENDAAVULSANOTAFISCALSAIDA WHERE STATUS_ID = ? AND (DATE_PART('day', NOW()::timestamp - DATA_EXPORTACAO::timestamp) * 24 + DATE_PART('hour', NOW()::timestamp - DATA_EXPORTACAO::timestamp)) * 60 + DATE_PART('minute', NOW()::timestamp - DATA_EXPORTACAO::timestamp) >=30", model.getStatus().getId());
        
        return broker.getCollectionBean(VendaAvulsaNotaFiscalSaida.class, "interfaceId");
        
	}	

	public VendaAvulsaNotaFiscalSaida obterIdExternoInterface(VendaAvulsaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID FROM VENDAAVULSANOTAFISCALSAIDA WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());
		
		return (VendaAvulsaNotaFiscalSaida) broker.getObjectBean(VendaAvulsaNotaFiscalSaida.class, "interfaceId");
	}	

}
