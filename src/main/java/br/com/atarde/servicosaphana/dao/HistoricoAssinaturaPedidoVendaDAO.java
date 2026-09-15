/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.AssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaLinha;
import br.com.atarde.servicosaphana.model.AssinaturaPedidoVendaParcela;
import br.com.atarde.servicosaphana.model.HistoricoAssinaturaPedidoVenda;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
public class HistoricoAssinaturaPedidoVendaDAO {

    public HistoricoAssinaturaPedidoVendaDAO() {
    }

	public HistoricoAssinaturaPedidoVenda inserirInterface(HistoricoAssinaturaPedidoVenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturapedidovenda_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_ASSINATURAPEDIDOVENDA(ID, INTERFACE_ORIGINAL_ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_PEDIDOVENDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_OBSERVACAO, CLIENTE_FLAG_ENDERECO,CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, FILIAL_ID, ARQUIVO_REMESSA, FLAG_REMESSA, SAP_DOCUMENTO_ID, FLAG_DOCUMENTO_EXISTENTE, FLAG_NOTA_FISCAL_SAIDA, ARQUIVO_REMESSA_SAP) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getInterfaceId(), model.getInterfaceOriginalId(), model.getCliente().getId(), model.getCliente().getTipo(), 
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
				model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(),  new Timestamp(System.currentTimeMillis()), model.getDataAtualizacao(), 
				model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), 
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getObservacao(), model.getUObservacao(),
				model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				model.getCliente().getEndereco().getTipoLogradouro(), model.getFilial().getId(), model.getArquivoRemessa(), model.getFlagRemessa(), model.getSapDocumentoId(), model.isFlagDocumentoExistente(), model.getFlagNotaFiscalSaida(), model.getArquivoRemessaSap());
		
		broker.execute();

		for (PedidoVendaLinhaAB item : model.getLinhas()) {

			AssinaturaPedidoVendaLinha linha = (AssinaturaPedidoVendaLinha) item;

			linha.setPedidoVenda(new AssinaturaPedidoVenda("interfaceId",model.getInterfaceId()));
			
			new HistoricoAssinaturaPedidoVendaLinhaDAO().inserirInterface(linha,broker);

		}
		
		if(!TSUtil.isEmpty(model.getParcelas())){
			
			for (ParcelaAB p : model.getParcelas()) {
				
				AssinaturaPedidoVendaParcela parcela = (AssinaturaPedidoVendaParcela) p;
				
				parcela.setPedidoVenda(new AssinaturaPedidoVenda("interfaceId",model.getInterfaceId()));
				
				new HistoricoAssinaturaPedidoVendaParcelaDAO().inserirInterface(parcela,broker);
				
			}
			
		}

		broker.endTransaction();

		return model;
		
	}

}
