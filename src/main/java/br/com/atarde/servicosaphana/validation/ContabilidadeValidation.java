package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.util.Date;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.dao.CentroCustoDAO;
import br.com.atarde.servicosaphana.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosaphana.sap.model.CentroCusto;
import br.com.atarde.servicosaphana.sap.model.Classificacao;
import br.com.atarde.servicosaphana.sap.model.ContaContabil;
import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.atarde.servicosaphana.sap.model.Estado;
import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosaphana.sap.model.Municipio;
import br.com.atarde.servicosaphana.sap.model.Pais;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class ContabilidadeValidation {

	public String validar(Contabilidade model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

			retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

			if (!TSUtil.isEmpty(model.getObservacao()) && (model.getObservacao().length() > 50)) {

				retorno.append(Constantes.CONTABILIDADE_OBSERVACAO + "\n");

			}

			if (TSUtil.isEmpty(model.getDataVencimento())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(model.getDataLancamento())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_LANCAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(model.getDataDocumento())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_DOCUMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(model.getDataExportacao())) {

				model.setDataExportacao(new Date(System.currentTimeMillis()));

			}

			if (TSUtil.isEmpty(model.getStatus())) {

				model.setStatus(new Status(Constantes.STATUS_NAO_PROCESSADO));

			}

			if (TSUtil.isEmpty(model.getLinhas()) || (model.getLinhas().size() <= 1)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTABILIDADE_LINHA + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				BigDecimal credito = BigDecimal.ZERO;

				BigDecimal debito = BigDecimal.ZERO;

				for (ContabilidadeLinha linha : model.getLinhas()) {

					if (!TSUtil.isEmpty(linha.getCodigoProjeto()) && (linha.getCodigoProjeto().length() > 20)) {

						retorno.append(Constantes.CONTABILIDADE_CODIGO_PROJETO + "\n");

					}

					if (!TSUtil.isEmpty(linha.getReferencia1()) && (linha.getReferencia1().length() > 100)) {

						retorno.append(Constantes.CONTABILIDADE_REFERENCIA1 + "\n");

					}

					if (!TSUtil.isEmpty(linha.getReferencia2()) && (linha.getReferencia2().length() > 100)) {

						retorno.append(Constantes.CONTABILIDADE_REFERENCIA2 + "\n");

					}
					
					if (!TSUtil.isEmpty(linha.getObservacao()) && (linha.getObservacao().length() > 50)) {

						retorno.append(Constantes.CONTABILIDADE_OBSERVACAO + "\n");

					}					

					if (TSUtil.isEmpty(linha.getValorCredito())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_VALOR_CREDITO + Constantes.CAMPO_OBRIGATORIO + "\n");

					} else {

						credito = credito.add(linha.getValorCredito());

					}

					if (TSUtil.isEmpty(linha.getValorDebito())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_VALOR_DEBITO + Constantes.CAMPO_OBRIGATORIO + "\n");

					} else {

						debito = debito.add(linha.getValorDebito());

					}

					if (!TSUtil.isEmpty(linha.getCentroCusto())) {

						if (TSUtil.isEmpty(linha.getCentroCusto().getDimensao()) || (TSUtil.isEmpty(Utilitarios.tratarLong(linha.getCentroCusto().getDimensao().getId())))) {

							retorno.append(Constantes.OBJETO_OBRIGATORIO_DIMENSAO + "\n");

						} else {

							linha.getCentroCusto().setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

							if (TSUtil.isEmpty(new CentroCustoDAO().obter(linha.getCentroCusto()))) {

								retorno.append(Constantes.OBJETO_OBRIGATORIO_CENTRO_CUSTO + "\n");

							}

						}

					} else {

						linha.setCentroCusto(new CentroCusto());

					}

					if (TSUtil.isEmpty(linha.getParceiroNegocio()) && (TSUtil.isEmpty(linha.getContaContabil()))) {

						retorno.append(Constantes.PARCEIRO_NEGOCIO_CONTA_CONTABIL + "\n");

					} else {

						if (!TSUtil.isEmpty(linha.getParceiroNegocio()) && (!TSUtil.isEmpty(linha.getContaContabil()))) {

							retorno.append(Constantes.PARCEIRO_NEGOCIO_CONTA_CONTABIL_LINHA + "\n");

						} else {

							if (!TSUtil.isEmpty(linha.getContaContabil())) {

								linha.getContaContabil().setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

								if (TSUtil.isEmpty(new ContaContabilDAO().obter(linha.getContaContabil()))) {

									retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + "\n");

								}

							} else {

								linha.setContaContabil(new ContaContabil());

							}

							if (!TSUtil.isEmpty(linha.getParceiroNegocio())) {

								linha.getParceiroNegocio().setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

								retorno.append(new ParceiroNegocioValidation().validar(linha.getParceiroNegocio()));

							}else{
								
								linha.setParceiroNegocio(new ParceiroNegocio());
								
								linha.getParceiroNegocio().setIdentificadorFiscal(new IdentificadorFiscal());
								
								linha.getParceiroNegocio().setEndereco(new ParceiroNegocioEndereco());
								
								linha.getParceiroNegocio().getEndereco().setEstado(new Estado());
								
								linha.getParceiroNegocio().getEndereco().setPais(new Pais());
								
								linha.getParceiroNegocio().getEndereco().setMunicipio(new Municipio());
								
								linha.getParceiroNegocio().setClassificacao(new Classificacao());
								
							}

						}

					}

				}

				if (credito.compareTo(debito) != 0 || (credito.equals(BigDecimal.ZERO) && debito.equals(BigDecimal.ZERO))) {

					retorno.append(Constantes.VALOR_CREDITO_DIFERENTE_VALOR_DEBITO + "\n");

				}

			}
		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

}
