/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.HistoricoClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class HistoricoClassificadosContratoNotaFiscalSaidaDAO {

    public HistoricoClassificadosContratoNotaFiscalSaidaDAO() {
    }

	public HistoricoClassificadosContratoNotaFiscalSaida inserirInterface(HistoricoClassificadosContratoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_contrato_nff_saida_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_CONTRATO_NFF_SAIDA (ID , INTERFACE_ORIGINAL_ID, CLIENTE_ID , CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR ,CLIENTE_NOME , CLIENTE_NOME_FANTASIA , CLIENTE_TELEFONE_RESIDENCIAL , CLIENTE_TELEFONE_CELULAR , CLIENTE_FAX , CLIENTE_EMAIL , CLIENTE_OBSERVACAO , CLIENTE_ENDERECO_LOGRADOURO , CLIENTE_ENDERECO_NUMERO , CLIENTE_ENDERECO_COMPLEMENTO , CLIENTE_ENDERECO_BAIRRO , CLIENTE_ENDERECO_CIDADE , CLIENTE_ENDERECO_ESTADO , CLIENTE_ENDERECO_CEP , CLIENTE_ENDERECO_PAIS , CLIENTE_ENDERECO_MUNICIPIO , CLIENTE_INSCRICAO_ESTADUAL , CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB , CLIENTE_INSCRICAO_MUNICIPAL , CLIENTE_INSCRICAO_INSS , CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID , CLIENTE_COBRANCA_ID , CLIENTE_COBRANCA_TIPO_IDENTIFICADOR , CLIENTE_COBRANCA_IDENTIFICADOR , CLIENTE_COBRANCA_NOME , CLIENTE_COBRANCA_NOME_FANTASIA , CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL , CLIENTE_COBRANCA_TELEFONE_CELULAR , CLIENTE_COBRANCA_FAX , CLIENTE_COBRANCA_EMAIL , CLIENTE_COBRANCA_OBSERVACAO , CLIENTE_COBRANCA_ENDERECO_LOGRADOURO , CLIENTE_COBRANCA_ENDERECO_NUMERO , CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO , CLIENTE_COBRANCA_ENDERECO_BAIRRO , CLIENTE_COBRANCA_ENDERECO_CIDADE , CLIENTE_COBRANCA_ENDERECO_ESTADO , CLIENTE_COBRANCA_ENDERECO_CEP , CLIENTE_COBRANCA_ENDERECO_PAIS , CLIENTE_COBRANCA_ENDERECO_MUNICIPIO , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB , CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL , CLIENTE_COBRANCA_INSCRICAO_INSS , CLIENTE_COBRANCA_DATA_ATUALIZACAO , CLIENTE_COBRANCA_CLASSIFICACAO_ID , VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR , VENDEDOR_IDENTIFICADOR , VENDEDOR_NOME , VENDEDOR_DATA_ATUALIZACAO , VENDEDOR_GRUPO_COMISSAO_ID , U_VENDEDOR_CGA , ID_EXTERNO , ORIGEM_ID , DATA_LANCAMENTO , DATA_DOCUMENTO , DATA_VENCIMENTO , CONDICAO_PAGAMENTO , U_VALOR_BRUTO , U_COMISSAO_AGENCIA , VALOR , DATA_CRIACAO , DATA_ATUALIZACAO , CRIADO_POR , ATUALIZADO_POR , DATA_EXPORTACAO , U_DIAS_PUBLICACAO , U_DATA_PUBLICACAO_FINAL , U_ENDERECO_ENTREGA , SEQUENCIA , U_TITULO_PUBLICACAO , STATUS_ID , DATA_IMPORTACAO , MENSAGEM_ERRO , U_NUMERO_PI , U_POSTO_ID , U_DATA_PUBLICACAO_INICIAL , EMPRESA_ID , FILIAL_ID , CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT , CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT , CLIENTE_FLAG_ENDERECO, CLIENTE_COBRANCA_FLAG_ENDERECO , SAP_NOTAFISCALSAIDA_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",  
				model.getInterfaceId(),
				model.getInterfaceOriginalId(),
                model.getAnunciante().getId(),
                model.getAnunciante().getIdentificadorFiscal().getTipoIdentificador(),
                model.getAnunciante().getIdentificadorFiscal().getIdentificador(),
                model.getAnunciante().getNome(),
                model.getAnunciante().getNomeFantasia(),
                model.getAnunciante().getTelefoneResidencial(),
                model.getAnunciante().getTelefoneCelular(),
                model.getAnunciante().getFax(),
                model.getAnunciante().getEmail(),
                model.getAnunciante().getObservacao(),
                model.getAnunciante().getEndereco().getLogradouro(),
                model.getAnunciante().getEndereco().getNumero(),
                model.getAnunciante().getEndereco().getComplemento(),
                model.getAnunciante().getEndereco().getBairro(),
                model.getAnunciante().getEndereco().getCidade(),
                model.getAnunciante().getEndereco().getEstado().getId(),
                model.getAnunciante().getEndereco().getCep(),
                model.getAnunciante().getEndereco().getPais().getId(),
                model.getAnunciante().getEndereco().getMunicipio().getId(),
                model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual(),
                model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(),
                model.getAnunciante().getIdentificadorFiscal().getInscricaoMunicipal(),
                model.getAnunciante().getIdentificadorFiscal().getInscricaoINSS(),
                model.getAnunciante().getDataAtualizacao(),
                model.getAnunciante().getClassificacao().getId(),
                
				model.getCliente().getId(),
				model.getCliente().getIdentificadorFiscal().getTipoIdentificador(),
                model.getCliente().getIdentificadorFiscal().getIdentificador(),
                model.getCliente().getNome(),
                model.getCliente().getNomeFantasia(),
                model.getCliente().getTelefoneResidencial(),
                model.getCliente().getTelefoneCelular(),
                model.getCliente().getFax(),
                model.getCliente().getEmail(),
                model.getCliente().getObservacao(),
                model.getCliente().getEndereco().getLogradouro(),
                model.getCliente().getEndereco().getNumero(),
                model.getCliente().getEndereco().getComplemento(),
                model.getCliente().getEndereco().getBairro(),
                model.getCliente().getEndereco().getCidade(),
                model.getCliente().getEndereco().getEstado().getId(),
                model.getCliente().getEndereco().getCep(),
                model.getCliente().getEndereco().getPais().getId(),
                model.getCliente().getEndereco().getMunicipio().getId(),
                model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(),
                model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(),
                model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(),
                model.getCliente().getIdentificadorFiscal().getInscricaoINSS(),
                model.getCliente().getDataAtualizacao(),
                model.getCliente().getClassificacao().getId(),
                
                model.getVendedor().getId(),
                model.getVendedor().getTipoIdentificador(),
                model.getVendedor().getIdentificador(),
                model.getVendedor().getNome(),
                model.getVendedor().getDataAtualizacao(),
                model.getVendedor().getGrupoComissao().getId(),
                model.getVendedor().getUCga(),
                
                model.getIdExterno(),
                model.getOrigem().getId(),
                model.getDataLancamento(),
                model.getDataDocumento(),
                model.getDataVencimento(),
                model.getCondicaoPagamento().getId(),
                model.getUValorBruto(),
                model.getUComissaoAgencia(),
                model.getValor(),
                model.getDataCriacao(),
                model.getDataAtualizacao(),
                model.getCriadoPor(),
                model.getAtualizadoPor(),
                model.getDataExportacao(),
                model.getUDiasPublicacao(),
                model.getUDataPublicacaoFinal(),
                model.getUEnderecoEntrega(),
                model.getSequencia().getId(),
                model.getUTituloPublicacao(),
                model.getStatus().getId(),
                new Timestamp(System.currentTimeMillis()),
                model.getMensagemErro(),
                model.getUNumeroPI(),
                model.getUPostoId(),
                model.getUDataPublicacaoInicial(),
                model.getEmpresa().getId(),
                model.getFilial().getId(),
                model.getCliente().getEnderecoEntregaDefault(),
                model.getCliente().getEnderecoCobrancaDefault(),
                model.getAnunciante().getEnderecoEntregaDefault(),
                model.getAnunciante().getEnderecoCobrancaDefault(),
                model.getAnunciante().getFlagEndereco(),
                model.getCliente().getFlagEndereco(),
                model.getId()
				);
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			ClassificadosContratoNotaFiscalSaidaLinha linha = (ClassificadosContratoNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new ClassificadosContratoNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new HistoricoClassificadosContratoNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}
		
		broker.endTransaction();

		return model;
		
	}

}
