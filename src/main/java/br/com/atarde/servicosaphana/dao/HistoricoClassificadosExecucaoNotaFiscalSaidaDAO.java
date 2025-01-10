/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.HistoricoClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class HistoricoClassificadosExecucaoNotaFiscalSaidaDAO {

    public HistoricoClassificadosExecucaoNotaFiscalSaidaDAO() {
    }

	public HistoricoClassificadosExecucaoNotaFiscalSaida inserirInterface(HistoricoClassificadosExecucaoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_classificados_execucao_nff_saida_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CLASSIFICADOS_EXECUCAO_NFF_SAIDA (ID , INTERFACE_ORIGINAL_ID, CLIENTE_COBRANCA_ID , CLIENTE_COBRANCA_TIPO_IDENTIFICADOR , CLIENTE_COBRANCA_IDENTIFICADOR , CLIENTE_COBRANCA_NOME , CLIENTE_COBRANCA_NOME_FANTASIA , CLIENTE_COBRANCA_TELEFONE_RESIDENCIAL , CLIENTE_COBRANCA_TELEFONE_CELULAR , CLIENTE_COBRANCA_FAX , CLIENTE_COBRANCA_EMAIL , CLIENTE_COBRANCA_OBSERVACAO , CLIENTE_COBRANCA_ENDERECO_LOGRADOURO , CLIENTE_COBRANCA_ENDERECO_NUMERO , CLIENTE_COBRANCA_ENDERECO_COMPLEMENTO , CLIENTE_COBRANCA_ENDERECO_BAIRRO , CLIENTE_COBRANCA_ENDERECO_CIDADE , CLIENTE_COBRANCA_ENDERECO_ESTADO , CLIENTE_COBRANCA_ENDERECO_CEP , CLIENTE_COBRANCA_ENDERECO_PAIS , CLIENTE_COBRANCA_ENDERECO_MUNICIPIO , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL , CLIENTE_COBRANCA_INSCRICAO_ESTADUAL_SUBTRIB , CLIENTE_COBRANCA_INSCRICAO_MUNICIPAL , CLIENTE_COBRANCA_INSCRICAO_INSS , CLIENTE_COBRANCA_DATA_ATUALIZACAO , CLIENTE_COBRANCA_CLASSIFICACAO_ID , VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR , VENDEDOR_IDENTIFICADOR , VENDEDOR_NOME , VENDEDOR_DATA_ATUALIZACAO , VENDEDOR_GRUPO_COMISSAO_ID , U_VENDEDOR_CGA , ID_EXTERNO , ORIGEM_ID , DATA_LANCAMENTO , DATA_DOCUMENTO , DATA_VENCIMENTO , CONDICAO_PAGAMENTO , SEQUENCIA , VALOR , DATA_CRIACAO , DATA_ATUALIZACAO , CRIADO_POR , ATUALIZADO_POR , DATA_EXPORTACAO , U_VALOR_BRUTO , U_ENDERECO_ENTREGA , STATUS_ID , DATA_IMPORTACAO , MENSAGEM_ERRO , EMPRESA_ID , FILIAL_ID , CLIENTE_COBRANCA_ENDERECO_ENTREGA_DEFAULT , CLIENTE_COBRANCA_ENDERECO_COBRANCA_DEFAULT, CLIENTE_COBRANCA_FLAG_ENDERECO , SAP_NOTAFISCALSAIDA_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",  
				model.getInterfaceId(),
                model.getInterfaceOriginalId(),
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
                model.getSequencia().getId(),
                model.getValor(),
                model.getDataCriacao(),
                model.getDataAtualizacao(),
                model.getCriadoPor(),
                model.getAtualizadoPor(),
                model.getDataExportacao(),
                model.getUValorBruto(),
                model.getUEnderecoEntrega(),
                model.getStatus().getId(),
                new Timestamp(System.currentTimeMillis()),
                model.getMensagemErro(),
                model.getEmpresa().getId(),
                model.getFilial().getId(),
                
                model.getCliente().getEnderecoEntregaDefault(),
                model.getCliente().getEnderecoCobrancaDefault(),
                model.getCliente().getFlagEndereco(),
                model.getId()
				);
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			ClassificadosExecucaoNotaFiscalSaidaLinha linha = (ClassificadosExecucaoNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new ClassificadosExecucaoNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new HistoricoClassificadosExecucaoNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}
		
		broker.endTransaction();

		return model;
		
	}

}
