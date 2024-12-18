package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContabilidadeLinhaDAO {

	public void inserir(ContabilidadeLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_contabilidades_linhas_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_CONTABILIDADES_LINHAS(ID, NUMERO, HISTORICO_CONTABILIDADE_ID, VALOR_CREDITO, VALOR_DEBITO, DATA_VENCIMENTO, DATA_LANCAMENTO, CODIGO_PROJETO, REFERENCIA1, REFERENCIA2, CONTA_CONTABIL_ID, CENTRO_CUSTO_ID, PARCEIRO_NEGOCIO_ID,PARCEIRO_NEGOCIO_TIPO_IDENTIFICADOR,PARCEIRO_NEGOCIO_IDENTIFICADOR,PARCEIRO_NEGOCIO_NOME,PARCEIRO_NEGOCIO_NOME_FANTASIA,PARCEIRO_NEGOCIO_TELEFONE_RESIDENCIAL,PARCEIRO_NEGOCIO_TELEFONE_CELULAR,PARCEIRO_NEGOCIO_FAX,PARCEIRO_NEGOCIO_EMAIL,PARCEIRO_NEGOCIO_OBSERVACAO,PARCEIRO_NEGOCIO_ENDERECO_LOGRADOURO,PARCEIRO_NEGOCIO_ENDERECO_NUMERO,PARCEIRO_NEGOCIO_ENDERECO_COMPLEMENTO,PARCEIRO_NEGOCIO_ENDERECO_BAIRRO,PARCEIRO_NEGOCIO_ENDERECO_CIDADE,PARCEIRO_NEGOCIO_ENDERECO_ESTADO,PARCEIRO_NEGOCIO_ENDERECO_CEP,PARCEIRO_NEGOCIO_ENDERECO_PAIS, PARCEIRO_NEGOCIO_ENDERECO_MUNICIPIO, PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL,PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL_SUBTRIB,PARCEIRO_NEGOCIO_INSCRICAO_MUNICIPAL,PARCEIRO_NEGOCIO_INSCRICAO_INSS,PARCEIRO_NEGOCIO_DATA_ATUALIZACAO,PARCEIRO_NEGOCIO_CLASSIFICACAO_ID,PARCEIRO_NEGOCIO_TIPO,PARCEIRO_NEGOCIO_FLAG_ENDERECO, PARCEIRO_NEGOCIO_ENDERECO_ENTREGA_DEFAULT, PARCEIRO_NEGOCIO_ENDERECO_COBRANCA_DEFAULT, OBSERVACAO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getNumero(), model.getContabilidade().getInterfaceId(), model.getValorCredito(), model.getValorDebito(), model.getDataVencimento(), model.getDataLancamento(), model.getCodigoProjeto(), model.getReferencia1(), model.getReferencia2(), model.getContaContabil().getId(), model.getCentroCusto().getId(), model.getParceiroNegocio().getId(), model.getParceiroNegocio()
				.getIdentificadorFiscal().getTipoIdentificador(), model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador(), model.getParceiroNegocio().getNome(), model.getParceiroNegocio().getNomeFantasia(), model.getParceiroNegocio().getTelefoneResidencial(), model.getParceiroNegocio().getTelefoneCelular(), model.getParceiroNegocio().getFax(), model.getParceiroNegocio().getEmail(), model.getParceiroNegocio().getObservacao(), model.getParceiroNegocio().getEndereco()
				.getLogradouro(), model.getParceiroNegocio().getEndereco().getNumero(), model.getParceiroNegocio().getEndereco().getComplemento(), model.getParceiroNegocio().getEndereco().getBairro(), model.getParceiroNegocio().getEndereco().getCidade(), model.getParceiroNegocio().getEndereco().getEstado().getId(), model.getParceiroNegocio().getEndereco().getCep(), model.getParceiroNegocio().getEndereco().getPais().getId(), model.getParceiroNegocio().getEndereco().getMunicipio().getId(), model
				.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS(), model.getParceiroNegocio().getDataAtualizacao(), model.getParceiroNegocio().getClassificacao().getId(), model.getParceiroNegocio().getTipo(),
				model.getParceiroNegocio().getFlagEndereco(), 	model.getParceiroNegocio().getEnderecoEntregaDefault(), model.getParceiroNegocio().getEnderecoCobrancaDefault(), model.getObservacao());

		broker.execute();

	}

}
