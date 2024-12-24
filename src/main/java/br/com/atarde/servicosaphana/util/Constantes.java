/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.util;

/**
 *
 * @author mroland
 */
public class Constantes {

	public static final String CAMPO_OBRIGATORIO = "Campo Obrigatorio";

	public static final String ERRO_TI = "Erro. Favor contactar a TI.";

	/* Conexoes */

	public static final String JNDI_SABWEB = "java:comp/env/jdbc/SapWebHanaDS";

	public static final String EMPRESA = "empresa";

	public static final String STATUS_BOLETO_PAGO = "P";

	public static final String STATUS_BOLETO_ENVIADO = "S";

	public static final String STATUS_BOLETO_GERADO = "G";

	public static final String STATUS_BOLETO_DEPOSITADO = "D";

	public static final String OBJETO_OBRIGATORIO_CENTRO_CUSTO = "Favor inserir objeto centroCusto.id válido.";

	public static final String OBJETO_OBRIGATORIO_CONTA_CONTABIL = "Favor inserir objeto ContaContabil ou contaContabil.id válido.";

	/* Parceiro Negocio */

	public static final String OBJETO_OBRIGATORIO_IDENTIFICADOR_FEDERAL = "Favor inserir atributo parceiroNegocio.identificadorFederal com tamanho limite: 32 caracteres.";

	public static final String OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL = "favor inserir atributo parceiroNegocio.identificadorFiscal.inscricaoEstadual com limite: 15 caracteres";

	public static final String PARCEIRO_NEGOCIO_OBSERVACAO = "Favor inserir atributo parceiroNegocio.observacao com tamanho limite: 100 caracteres.";

	public static final String PARCEIRO_NEGOCIO_TELEFONE_EMAIL = "Favor inserir atributo parceiroNegocio.email com tamanho limite: 50 caracteres.";

	public static final String PARCEIRO_NEGOCIO_TELEFONE_FAX = "Favor inserir atributo parceiroNegocio.fax com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_TELEFONE_CELULAR = "Favor inserir atributo parceiroNegocio.celular com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_TELEFONE_OBRIGATORIO = "Favor inserir atributo parceiroNegocio.celular ou parceiroNegocio.telefoneResidencial.";

	public static final String PARCEIRO_NEGOCIO_TELEFONE_RESIDENCIAL = "Favor inserir atributo parceiroNegocio.telefoneResidencial com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_INSCRICAO_INSS = "Favor inserir atributo parceiroNegocio.identificadorFiscal.inscricaoINSS com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_INSCRICAO_MUNICIPAL = "Favor inserir atributo parceiroNegocio.identificadorFiscal.inscricaoMunicipal com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL_SUBSTITUTO_TRIBUTARIA = "Favor inserir atributo parceiroNegocio.identificadorFiscal.inscricaoEstadualSubstitutoTributaria com tamanho limite: 15 caracteres.";

	public static final String PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL = "Favor inserir atributo parceiroNegocio.identificadorFiscal.inscricaoEstadual com tamanho limite: 15 caracteres.";

	public static final String OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO = "Favor inserir objeto ParceiroNegocio ou parceiroNegocio.id válido.";

	public static final String OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_TIPO_IDENTIFICADOR = "Favor inserir atributo parceiroNegocio.identificadorFiscal.tipoIdentificador - (0)CNPJ | (1)CPF | (2)OUTROS.";

	public static final String OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_IDENTIFICADOR = "Favor inserir atributo parceiroNegocio.identificadorFiscal.identificador com tamanho limite: 20 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOME = "Favor inserir atributo parceiroNegocio.nome com tamanho limite: 100 caracteres.";

	public static final String PARCEIRO_NEGOCIO_NOME_FANTASIA = "Favor inserir atributo parceiroNegocio.nomeFantasia com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_CLASSIFICACAO = "Favor inserir objeto parceiroNegocio.classificacao ou classificacao.id válido.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO = "Favor inserir Objeto Endereco do Objeto Cliente";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_LOGRADOURO = "Favor inserir atributo parceiroNegocio.endereco.logradouro com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_TIPO_LOGRADOURO = "Favor inserir atributo parceiroNegocio.endereco.tipoLogradouro com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_NUMERO = "Favor inserir atributo parceiroNegocio.endereco.número com tamanho limite: 20 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_BAIRRO = "Favor inserir atributo parceiroNegocio.endereco.bairro com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_CIDADE = "Favor inserir atributo parceiroNegocio.endereco.cidade com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_ESTADO = "Favor inserir atributo parceiroNegocio.endereco.estado.id válido com tamanho limite: 3 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_ESTADO_DESCRICAO = "Favor inserir atributo parceiroNegocio.endereco.estado.descricao.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_CEP = "Favor inserir atributo parceiroNegocio.endereco.cep com tamanho limite: 20 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_PAIS = "Favor inserir atributo parceiroNegocio.endereco.país.id válido com tamanho limite: 3 caracteres.";

	public static final String OBJETO_OBRIGATORIO_ENDERECO_PAIS_DESCRICAO = "Favor inserir atributo parceiroNegocio.endereco.país.descricao.";

	public static final String MUNICIPIO_INEXISTENTE = "Favor informar o pais/estado/cidade válido´s.";

	public static final String OBJETO_OBRIGATORIO_TIPO_PARCEIRO_NEGOCIO = "Favor inserir atributo parceiroNegocio.tipo - (C)Cliente | (S)Fornecedor.";

	public static final String OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_VENCIMENTO = "Favor inserir atributo contabilidade.dataVencimento.";

	public static final String OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_LANCAMENTO = "Favor inserir atributo contabilidade.dataLancamento.";

	public static final String OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_DOCUMENTO = "Favor inserir atributo contabilidade.dataDocumento.";

	public static final String OBJETO_OBRIGATORIO_VALOR_CREDITO = "Favor inserir atributo contabilidadeLinha.valorCredito.";

	public static final String OBJETO_OBRIGATORIO_VALOR_DEBITO = "Favor inserir atributo contabilidadeLinha.valorDebito.";

	public static final String OBJETO_OBRIGATORIO_DIMENSAO = "Favor inserir objeto Dimensao ou dimensao.id válido para objeto do tipo RegraDistribuicao.";

	public static final String TIPO_PARCEIRO_NEGOCIO_CLIENTE = "C";

	public static final Long STATUS_NAO_PROCESSADO = 0L;

	public static final Long USUARIO_ADM = 1L;

	public static final String OBJETO_EMPRESA_OBRIGATORIO = "Favor inserir objeto Empresa ou empresa.id válida.";

	public static final Object OBJETO_PARCEIRO_NEGOCIO_TIPO = "Favor informar parceiroNegocio.tipo: {'C'- Cliente | 'S' - Fornecedor }";

	public static final String OBJETO_OBRIGATORIO_TIPO_IDENTIFICADOR = "Favor inserir objeto IdentificadorFiscal ou atributo tipoIdentificador - (0)CNPJ | (1)CPF | (2)OUTROS.";

	/* Contabilidade */

	public static final String PARCEIRO_NEGOCIO_CONTA_CONTABIL = "Favor inserir no objeto contabilidadeLinha o atributo parceiroNegocio ou contaContabil.";

	public static final String OBJETO_OBRIGATORIO_NUMERO = "Favor inserir atributo contabilidadeLinha.numero.";

	public static final String VALOR_CREDITO_VALOR_DEBITO = "Favor inserir outro valor para valorDebito e valorCrédito.";

	public static final String VALOR_CREDITO_DIFERENTE_VALOR_DEBITO = "A soma dos créditos é diferente dos débitos ou créditos e débitos são iguais a zero.";

	public static final String PARCEIRO_NEGOCIO_CONTA_CONTABIL_LINHA = "Não pode haver para uma mesma linha objetos parceiroNegocio e contaContabil.";

	public static final String CONTABILIDADE_OBSERVACAO = "Atributo contabilidade.observacao tamanho limite: 50 caracteres.";

	public static final String CONTABILIDADE_CODIGO_PROJETO = "Atributo contabilidade.codigoProjeto tamanho limite: 20 caracteres.";

	public static final String CONTABILIDADE_REFERENCIA1 = "Atributo contabilidade.referencia1 tamanho limite: 100 caracteres.";

	public static final String CONTABILIDADE_REFERENCIA2 = "Atributo contabilidade.referencia2 tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_CONTABILIDADE_LINHA = "Favro inserir objeto ContabilidadeLinha ou inserir mais de uma linha.";

	/* Contas Receber */

	public static final String CONTAS_RECEBER = "Favor inserir objeto ContasReceber.";

	public static final String OBJETO_OBRIGATORIO_CONTAS_RECEBER_VALOR = "Favor informar atributo valor para objeto ContasReceber.";

	public static final String OBJETO_OBRIGATORIO_CONTAS_RECEBER_DATA_TRANSFERENCIA = "Favor informar atributo dataTransferencia.";

	public static final String OBJETO_OBRIGATORIO_MODALIDADE_TRANSFERENCIA = "Favor informar o objeto modalidadePagamentoTransferencia.";

	public static final String OBJETO_OBRIGATORIO_FORMA_PAGAMENTO = "Favor informar o objeto formaPagamento ou formaPagamento.id válido.";

	public static final String OBJETO_OBRIGATORIO_CONTAS_RECEBER_LINHA_VALOR_APLICADO = "Favor informar atributo valorAplicado do objeto contasReceberLinha.";

	public static final String TIPO_MODALIDADE_BOLETO = "B";

	public static final String TIPO_MODALIDADE_TRANSFERENCIA = "T";

	/* Parcela */

	public static final String OBJETO_OBRIGATORIO_PARCELA_ID = "Favor informar atributo id do objeto ParcelaNotaFiscalSaida";

	public static final String OBJETO_OBRIGATORIO_PARCELA_NUMERO = "Favor informar atributo numero do objeto ParcelaNotaFiscalSaida";

	public static final String OBJETO_OBRIGATORIO_PARCELA_VALORABERTO = "Favor informar atributo valorAberto do objeto ParcelaNotaFiscalSaida";

	public static final String OBJETO_OBRIGATORIO_CONTAS_RECEBER_LINHA = "Favor informar atributo lista contasReceberLinhaList do objeto contasReceber.";

	public static final String OBJETO_OBRIGATORIO_PARCELA = "Favor informar atributo parcelaNotsFiscalSaida da lista contasReceberLinhaList.";

	/* Nota Fiscal Saida */

	public static final String NOTAFISCALSAIDA = "Favor inserir objeto NotaFiscalSaida.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_OBSERVACAO = "favor informar atributo observacao do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO = "favor informar atributo uObservacao do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_TERMO = "favor informar atributo uTermo do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_BANCA = "favor informar atributo uBanca do Objeto NotaFiscalSaida com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_LOTE = "favor informar atributo uLote do Objeto NotaFiscalSaida com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOBANCA = "favor informar atributo uTipoBanca do Objeto NotaFiscalSaida com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOFATURAMENTO = "favor informar atributo uTipoFaturamento do Objeto NotaFiscalSaida com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA = "favor informar atributo uEnderecoEntrega do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_NUMERO_PI = "favor informar atributo uNumeroPI do Objeto NotaFiscalSaida com tamanho limite: 32 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_TIPO_TRANSACAO = "favor informar atributo uTipoTransacao do Objeto NotaFiscalSaida com tamanho limite: 10 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PERIODO = "favor informar atributo uPeriodo do Objeto NotaFiscalSaida com tamanho limite: 30 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_FORMATO = "favor informar atributo uFormato do Objeto NotaFiscalSaida com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PAGEVIEWS = "favor informar atributo uPageViews do Objeto NotaFiscalSaida com valor diferente de 0(zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENTREGA_VENDEDOR = "favor informar atributo uEntregaVendedor do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PRODUTO = "favor informar atributo uProduto do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_CAMPANHA = "favor informar atributo uCampanha do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_POSTO_ID = "favor informar atributo uPostoId do Objeto NotaFiscalSaida com tamanho limite: 64 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_VALOR_BRUTO = "favor inserir atributo atributo uValorBruto do Objeto NotaFiscalSaida.";

	public static final String OBJETO_OBRIGATORIO_COMISSAO_AGENCIA = "favor inserir uComissaoAgencia do objeto NotaFiscalSaida.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_U_DATA_PUBLICACAO_FINAL = "favor inserir atributo uDataPublicacaoFinal do Objeto NotaFiscalSaida.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_U_DATA_PUBLICACAO_INICIAL = "favor inserir atributo uDataPublicacaoInicial do Objeto NotaFiscalSaida.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_U_DIAS_PUBLICACAO = "favor inserir atributo uDiasPublicacao do Objeto NotaFiscalSaida com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_U_TITULO_PUBLICACAO = "favor inserir atributo uTituloPublicacao do Objeto NotaFiscalSaida com tamanho limite: 254 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_ID_EXTERNO = "Favor inserir atributo idExterno do Objeto DocumentoAB. ";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_LANCAMENTO = "Favor inserir atributo dataLancamento do Objeto DocumentoAB. ";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_DOCUMENTO = "Favor inserir atributo dataDocumento do Objeto DocumentoAB. ";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_VENCIMENTO = "Favor inserir atributo dataVencimento do Objeto DocumentoAB. ";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_CRIACAO = "Favor inserir atributo dataCriacao do Objeto DocumentoAB. ";

	public static final String DOCUMENTOEXPORTADO = "Documento ja existe na base Produção. Favor cancelar antes de exportar novamente.";

	public static final String OBJETO_OBRIGATORIO_DOCUMENTOAB_VALOR = "Favor inserir atributo valor do Objeto DocumentoAB maior que 0(Zero).";

	/* NotaFiscalSaidaLinha */

	public static final String OBJETO_OBRIGATORIO_DOCUMENTOAB_LINHA = "Favor inserir objeto DocumentoLinhaAB.";

	public static final String OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA = "Favor inserir lista documentoAB.linhas ou objeto DocumentoLinhaAB.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM = "Favor inserir objeto Item ou atributo Item.id do Objeto DocumentoLinhaAB válido.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE = "Favor inserir objeto Item.estoque ou atributo Item.estoque.id do Objeto DocumentoLinhaAB válido.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE_CONTROLE = "Favor inserir objeto Item.estoque ou atributo Item.estoque.id do Objeto DocumentoLinhaAB válido ou estoque é insuficiente.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_TIPO_ITEM_OBRIGATORIO = "O item informado não é de estoque nem de venda. Favor procurar a TI.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_QUANTIDADE = "Favor inserir atributo quantidade do Objeto DocumentoLinhaAB.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_VALOR = "Favor inserir atributo valor do Objeto DocumentoLinhaAB maior que 0(Zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_CODIGO_IMPOSTO = "Favor inserir atributo códigoImposto do Objeto DocumentoLinhaAB ou codigoImposto.id válido.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_UTILIZACAO = "Favor inserir atributo utilizacao do Objeto DocumentoLinhaAB ou utilizacao.id válido.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_CMXCOL = "Favor inserir atributo uCMxCol do Objeto DocumentoLinhaAB.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_AREA = "Favor inserir atributo uArea do Objeto DocumentoLinhaAB com valor diferente de 0(Zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_VALOR_UNITARIO = "Favor inserir atributo uValorUnitario do Objeto DocumentoLinhaAB com valor diferente de 0(Zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_TOTAL_CMXCOL = "Favor inserir atributo uTotalCMxCol do Objeto DocumentoLinhaAB com valor diferente de 0(Zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_QUANTIDADE_INSERCOES = "Favor inserir atributo uQuantidadeInsercoes do Objeto DocumentoLinhaAB com valor diferente de 0(Zero).";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_FLAG_IMPOSTO = "Favor inserir atributo flagImposto do do Objeto DocumentoLinhaAB.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_SEQUENCIA = "Favor inserir atributo sequencia do Objeto DocumentoAB ou sequencia.id válido.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_CONDICAO_PAGAMENTO_DATA_VENCIMENTO = "Favor inserir atributo dataVencimento ou Objeto CondicaoPagamento ou atributo CondicaoPagamento.id válido.";

	public static final String CFOP_INEXISTENTE = "favor informar CFOP válido.";

	public static final String CSTICMS_INEXISTENTE = "favor informar atributo cstICMS.codigo do Objeto DocumentoLinhaAB válido.";

	public static final String CSTIPI_INEXISTENTE = "favor informar atributo cstIPI.codigo do Objeto DocumentoLinhaAB válido.";

	public static final String CSTPIS_INEXISTENTE = "favor informar atributo cstPIS.codigo do Objeto DocumentoLinhaAB válido.";

	public static final String CSTCOFINS_INEXISTENTE = "favor informar atributo cstCOFINS.codigo do Objeto DocumentoLinhaAB válido.";

	/* Romaneios */

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ROTEIRO = "favor informar atributo notaFiscalSaidaAB.romaneios.roterio com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ID_EXTERNO = "favor informar atributo notaFiscalSaidaAB.romaneios.idExterno com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_DESCRICAO = "favor informar atributo notaFiscalSaidaAB.romaneios.descricao com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_DATA = "favor informar atributo notaFiscalSaidaAB.romaneios.data.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_REPARTE = "favor informar atributo notaFiscalSaidaAB.romaneios.reparte.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ENCALHE = "favor informar atributo notaFiscalSaidaAB.romaneios.encalhe.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_VENDA = "favor informar atributo notaFiscalSaidaAB.romaneios.venda.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_PRECO = "favor informar atributo notaFiscalSaidaAB.romaneios.preco.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_VALOR = "favor informar atributo notaFiscalSaidaAB.romaneios.valor.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_RDJ = "favor informar atributo notaFiscalSaidaAB.romaneios.rdj com tamanho limite: 50 caracteres.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_REGIAO = "favor informar atributo notaFiscalSaidaAB.romaneios.regiao com tamanho limite: 1 caracter.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_CIDADE = "favor informar atributo notaFiscalSaidaAB.romaneios.cidade com tamanho limite: 50 caracteres.";

	/* Entrega */

	public static final String ENTREGA = "Favor inserir objeto Entrega.";

	/* Devolucao */

	public static final String DEVOLUCAO = "Favor inserir objeto Devolucao.";

	/* Vendedor */

	public static final String OBJETO_OBRIGATORIO_VENDEDOR = "Favor inserir objeto Vendedor ou atributo vendedor.id válido.";

	public static final String OBJETO_OBRIGATORIO_VENDEDOR_GRUPO_COMISSAO = "Favor inserir objeto vendedor.grupoComissao ou atributo vendedor.grupoComissao.id válido.";

	public static final String OBJETO_OBRIGATORIO_VENDEDOR_DATA_ATUALIZACAO = "Favor inserir atributo vendedor.dataAtualizacao.";

	public static final String OBJETO_OBRIGATORIO_VENDEDOR_NOME = "Favor inserir atributo vendedor.nome com tamanho limite: 100 caracteres.";

	public static final String OBJETO_OBRIGATORIO_VENDEDOR_IDENTIFICADOR = "Favor inserir atributo vendedor.identificador com tamanho limite: 20 caracteres.";

	public static final String OBJETO_OBRIGATORIO_VENDEDOR_TIPO_IDENTIFICADOR = "Favor inserir atributo vendedor.tipoIdentificador - (0)CNPJ | (1)CPF | (2)OUTROS.";

	public static final String OBJETO_OBRIGATORIO_PEDIDO_VENDA_LINHA_NUMERO = "Favor inserir atributo pedidoVendaLinha.numero.";

	public static final Object OBJETO_OBRIGATORIO_PEDIDO_VENDA = "Favor inserir atributo pedidoVenda ou pedidoVenda.id válido.";

	/* Estoque */

	public static final String ESTOQUE = "favor inserir objeto Estoque.";

	public static final String PEDIDOVENDA = "Favor inserir objeto PedidoVenda.";

	public static final String PARCEIRO_NEGOCIO_ENDERECO_ID = "Favor inserir atributo parceiroNegocio.endereco.id válido.";

	public static final Long GRUPO_COMISSAO_AGENCIA = 1L;

	public static final Long GRUPO_COMISSAO_CORRETORES = 3L;

	public static final Long GRUPO_COMISSAO_SVG = 6L;

	/* Extrato Bancario */

	public static final String OBJETO_OBRIGATORIO_EXTRATO_BANCARIO_DATA_VENCIMENTO = "Favor inserir atributo extratoBancario.dataVencimento.";

	public static final String OBJETO_OBRIGATORIO_EXTRATO_BANCARIO_DETALHE = "Favor inserir atributo extratoBancario.detalhe com tamanho limite: 20 caracteres.";

	public static final String OBJETO_EXTRATO_BANCARIO_CONTA = "Favor inserir atributo extratoBancario.conta com tamanho limite: 50 caracteres.";

	public static final String OBJETO_EXTRATO_BANCARIO_NUMERO_DOCUMENTO = "Favor inserir atributo extratoBancario.numeroDocumento com tamanho limite: 10 caracteres.";

	public static final String OBJETO_EXTRATO_BANCARIO_DEBITO_CREDITO_ZERO = "Favor inserir atributo extratoBancario.valorCredito ou extratoBancario.valorDebito maior que 0(zero).";

	public static final String OBJETO_OBRIGATORIO_CODIGO_INTERNO = "Favor inserir objeto extratoBancario.codigoInterno ou extratoBancario.codigoInterno.id válido.";

	/* Condicao Pagamento Parcela */

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_PARCELA_DATA_VENCIMENTO = "favor inserir atributo notaFiscalSaidaAB.parcelas.dataVencimento com valor superior ou igual a data atual.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_PARCELA_VALOR = "favor inserir atributo notaFiscalSaidaAB.parcelas.valor com valor>0.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_VALOR_PARCELAS = "O valor das parcelas é diferente do valor total da nota.";

	public static final String OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_QTD_PARCELAS = "A quantidade de parcelas é diferente da condicao de pagamento no SAP.";

	public static final String OBJETO_OBRIGATORIO_CONDICAO_PAGAMENTO_QTD_PARCELAS = "favor requisitar a TI para inserir as parcelas em condicao pagamento no SAP.";

	public static final Integer TIPO_DOCUMENTO_SEQUENCIA_NOTA = 1;

}
