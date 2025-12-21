/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.HistoricoRadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaidaParcela;
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
public class HistoricoRadioNotaFiscalSaidaDAO {

	public HistoricoRadioNotaFiscalSaidaDAO() {
	}

	public HistoricoRadioNotaFiscalSaida inserirInterface(HistoricoRadioNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_radio_nff_saida_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_RADIO_NFF_SAIDA (ID, INTERFACE_ORIGINAL_ID, CLIENTE_ID, CLIENTE_TIPO_IDENTIFICADOR,CLIENTE_IDENTIFICADOR,CLIENTE_NOME,CLIENTE_NOME_FANTASIA,CLIENTE_TELEFONE_RESIDENCIAL,CLIENTE_TELEFONE_CELULAR,CLIENTE_FAX,CLIENTE_EMAIL,CLIENTE_OBSERVACAO,CLIENTE_ENDERECO_LOGRADOURO,CLIENTE_ENDERECO_NUMERO,CLIENTE_ENDERECO_COMPLEMENTO,CLIENTE_ENDERECO_BAIRRO,CLIENTE_ENDERECO_CIDADE,CLIENTE_ENDERECO_ESTADO,CLIENTE_ENDERECO_CEP,CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL,CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_INSCRICAO_MUNICIPAL,CLIENTE_INSCRICAO_INSS,CLIENTE_DATA_ATUALIZACAO,CLIENTE_CLASSIFICACAO_ID, CLIENTE_COBRANCA_ID, CLIENTE_COBRANCA_TIPO_IDENTIFICADOR,CLIENTE_COBRANCA_IDENTIFICADOR,CLIENTE_COBRANCA_NOME,CLIENTE_COBRANCA_NOME_FANTASIA,CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL,CLIENTE_COBRANCA_TELEFONE_CELULAR,CLIENTE_COBRANCA_FAX,CLIENTE_COBRANCA_EMAIL,CLIENTE_COBRANCA_OBSERVACAO,CLIENTE_COBRANCA_ENDERECO_LOGRADOURO,CLIENTE_COBRANCA_ENDERECO_NUMERO,CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO,CLIENTE_COBRANCA_ENDERECO_BAIRRO,CLIENTE_COBRANCA_ENDERECO_CIDADE,CLIENTE_COBRANCA_ENDERECO_ESTADO,CLIENTE_COBRANCA_ENDERECO_CEP,CLIENTE_COBRANCA_ENDERECO_PAIS, CLIENTE_COBRANCA_ENDERECO_MUNICIPIO,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL,CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB,CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL,CLIENTE_COBRANCA_INSCRICAO_INSS,CLIENTE_COBRANCA_DATA_ATUALIZACAO,CLIENTE_COBRANCA_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR,VENDEDOR_IDENTIFICADOR,VENDEDOR_NOME,VENDEDOR_DATA_ATUALIZACAO,VENDEDOR_GRUPO_COMISSAO_ID, U_VENDEDOR_CGA, ID_EXTERNO,ORIGEM_ID,DATA_LANCAMENTO,DATA_DOCUMENTO,DATA_VENCIMENTO,CONDICAO_PAGAMENTO, SEQUENCIA, VALOR, DATA_CRIACAO,DATA_ATUALIZACAO,CRIADO_POR,ATUALIZADO_POR,DATA_EXPORTACAO, U_VALOR_BRUTO,U_COMISSAO_AGENCIA, U_ENDERECO_ENTREGA, STATUS_ID, DATA_IMPORTACAO, MENSAGEM_ERRO, U_NUMERO_PI, U_DATA_CONTRATO, U_PERIODO_VEICULACAO, U_TIPO_TRANSACAO, U_ENTREGA_VENDEDOR, U_POSTO_ID, U_DATA_PUBLICACAO_INICIAL, U_OBSERVACAO, U_PERMUTA, EMPRESA_ID, CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT, CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_COBRANCA_FLAG_ENDERECO, CLIENTE_FLAG_ENDERECO, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, FILIAL_ID, SAP_NOTAFISCALSAIDA_ID, ARQUIVO_REMESSA, SAP_DOCUMENTO_ID, FLAG_DOCUMENTO_EXISTENTE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
				
				model.getInterfaceId(),
				
				model.getInterfaceOriginalId(),

				model.getAnunciante().getId(), model.getAnunciante().getIdentificadorFiscal().getTipoIdentificador(), model.getAnunciante().getIdentificadorFiscal().getIdentificador(), model.getAnunciante().getNome(), model.getAnunciante().getNomeFantasia(), model.getAnunciante().getTelefoneResidencial(), model.getAnunciante().getTelefoneCelular(), model.getAnunciante().getFax(), model.getAnunciante().getEmail(), model.getAnunciante().getObservacao(), model.getAnunciante().getEndereco().getLogradouro(), model.getAnunciante().getEndereco().getNumero(), model.getAnunciante().getEndereco().getComplemento(), model.getAnunciante().getEndereco().getBairro(), model.getAnunciante().getEndereco().getCidade(), model.getAnunciante().getEndereco().getEstado().getId(), model.getAnunciante().getEndereco().getCep(), model.getAnunciante().getEndereco().getPais().getId(), model.getAnunciante().getEndereco().getMunicipio().getId(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getAnunciante().getIdentificadorFiscal().getInscricaoMunicipal(), model.getAnunciante().getIdentificadorFiscal().getInscricaoINSS(), model.getAnunciante().getDataAtualizacao(), model.getAnunciante().getClassificacao().getId(),

				model.getCliente().getId(), model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(),

				model.getVendedor().getId(), model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(),

				model.getIdExterno(), model.getOrigem().getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getSequencia().getId(), model.getValor(), model.getDataCriacao(), model.getDataAtualizacao(), model.getCriadoPor(), model.getAtualizadoPor(), model.getDataExportacao(), model.getUValorBruto(), model.getUComissaoAgencia(), model.getUEnderecoEntrega(), model.getStatus().getId(), new Timestamp(System.currentTimeMillis()), model.getMensagemErro(), model.getUNumeroPI(), model.getUDataContrato(), model.getUPeriodoVeiculacao(), model.getUTipoTransacao(), model.getUEntregaVendedor(), model.getUPostoId(), model.getUDataPublicacaoInicial(), model.getObservacao(), model.getUPermuta(), model.getEmpresa().getId(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(), model.getCliente().getFlagEndereco(), model.getAnunciante().getFlagEndereco(), model.getAnunciante().getEnderecoEntregaDefault(), model.getAnunciante().getEnderecoCobrancaDefault(), model.getFilial().getId(), model.getId(), model.getArquivoRemessa(),
				
				model.getSapDocumentoId(), model.isFlagDocumentoExistente()

		);

		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			RadioNotaFiscalSaidaLinha linha = (RadioNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new RadioNotaFiscalSaida("interfaceId", model.getInterfaceId()));

			new HistoricoRadioNotaFiscalSaidaLinhaDAO().inserirInterface(linha, broker);

		}
		
		if(!TSUtil.isEmpty(model.getParcelas())){
			
			for (ParcelaAB p : model.getParcelas()) {
				
				RadioNotaFiscalSaidaParcela parcela = (RadioNotaFiscalSaidaParcela) p;
				
				parcela.setNotaFiscalSaida(new RadioNotaFiscalSaida("interfaceId",model.getInterfaceId()));
				
				new HistoricoRadioNotaFiscalSaidaParcelaDAO().inserirInterface(parcela,broker);
				
			}
			
		}

		broker.endTransaction();

		return model;

	}

}
