package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.util.Date;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosaphana.sap.dao.FormaPagamentoDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceberLinha;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public class ContasReceberValidation {

	public String validar(ContasReceber model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.CONTAS_RECEBER + "\n");

		} else {
			
			if(TSUtil.isEmpty(model.getEmpresa())){
				
				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO + "\n");				
				
			}else{
				
				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
				
			}

			retorno.append(this.validarGlobal(model));

			if (!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())) {

				retorno.append(this.validarBoleto(model));

			} else {

				if (!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia())) {

					retorno.append(this.validarTransferencia(model));
				}

			}

		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();
	}

	private String validarTransferencia(ContasReceber model) {

		StringBuilder retorno = new StringBuilder();

		if (!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia().getContaContabil())) {

			model.getModalidadePagamentoTransferencia().getContaContabil().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new ContaContabilDAO().obter(model.getModalidadePagamentoTransferencia().getContaContabil()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		} else {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getModalidadePagamentoTransferencia().getDataTransferencia())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTAS_RECEBER_DATA_TRANSFERENCIA + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			if (TSUtil.isEmpty(model.getDataDocumento())) {

				model.setDataDocumento(new Date());

			}

			if (TSUtil.isEmpty(model.getDataLancamento())) {

				model.setDataLancamento(new Date());

			}

			model.setDataVencimento(model.getModalidadePagamentoTransferencia().getDataTransferencia());

		}

		return retorno.toString();

	}

	private String validarBoleto(ContasReceber model) {

		StringBuilder retorno = new StringBuilder();

		if (!TSUtil.isEmpty(model.getModalidadePagamentoBoleto().getFormaPagamento())) {

			model.getModalidadePagamentoBoleto().getFormaPagamento().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new FormaPagamentoDAO().obter(model.getModalidadePagamentoBoleto().getFormaPagamento()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_FORMA_PAGAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		} else {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_FORMA_PAGAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getModalidadePagamentoBoleto().getDataVencimento())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTABILIDADE_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			if (TSUtil.isEmpty(model.getDataDocumento())) {

				model.setDataDocumento(new Date());

			}

			if (TSUtil.isEmpty(model.getDataLancamento())) {

				model.setDataLancamento(new Date());

			}

			model.setDataVencimento(model.getModalidadePagamentoBoleto().getDataVencimento());

		}

		return retorno.toString();

	}

	private String validarGlobal(ContasReceber model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getCliente())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getCliente().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new ParceiroNegocioDAO().obter(model.getCliente()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}
		}

		if (TSUtil.isEmpty(model.getValor()) || (model.getValor().equals(BigDecimal.ZERO))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTAS_RECEBER_VALOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			if (!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())) {

				model.getModalidadePagamentoBoleto().setValor(model.getValor());

				if (!TSUtil.isEmpty(model.getModalidadePagamentoBoleto().getFormaPagamento())) {

					model.getModalidadePagamentoBoleto().getFormaPagamento().setTipo("I");

				}

			} else {

				model.getModalidadePagamentoTransferencia().setValor(model.getValor());

			}

		}

		if (TSUtil.isEmpty(model.getLinhas())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTAS_RECEBER_LINHA + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			for (ContasReceberLinha linha : model.getLinhas()) {
				
				if(TSUtil.isEmpty(linha.getParcela())){
					
					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCELA + Constantes.CAMPO_OBRIGATORIO + "\n");
					
				}else{
					
					if (TSUtil.isEmpty(linha.getParcela().getId())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCELA_ID + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

					if (TSUtil.isEmpty(linha.getParcela().getNumero())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCELA_NUMERO + Constantes.CAMPO_OBRIGATORIO + "\n");

					}
								
				}
				
				if (TSUtil.isEmpty(linha.getValorAplicado())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTAS_RECEBER_LINHA_VALOR_APLICADO + Constantes.CAMPO_OBRIGATORIO + "\n");

				}


			}

		}

		return retorno.toString();
	}

}
