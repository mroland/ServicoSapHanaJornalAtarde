package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.dao.CondicaoPagamentoDAO;
import br.com.atarde.servicosaphana.sap.dao.NotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.sap.dao.SequenciaDAO;
import br.com.atarde.servicosaphana.sap.model.CondicaoPagamento;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.ParcelaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public class NotaFiscalSaidaValidation extends DocumentoValidationAB {

	public String validar(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.NOTAFISCALSAIDA + "\n");

		} else {

			if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO);

			} else {

				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

				if (!TSUtil.isEmpty(model.getCliente())) {

					model.getCliente().setEmpresa(model.getEmpresa());

				}

				if (!TSUtil.isEmpty(model.getVendedor())) {

					model.getVendedor().setEmpresa(model.getEmpresa());

				}

				retorno.append(super.validar(model));

				retorno.append(new ParceiroNegocioValidation().validar(model.getCliente()));

				retorno.append(new VendedorValidation().validar(model.getVendedor()));

				retorno.append(this.validaNFF(model));

			}

		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();
	}

	private String validaNFF(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();
		
		if(TSUtil.isEmpty(model.getIdExterno())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_ID_EXTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			if (!TSUtil.isEmpty(new NotaFiscalSaidaDAO().obterIdExterno(model))) {

				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");

			} else {

				if (TSUtil.isEmpty(model.getValor()) || model.getValor().compareTo(BigDecimal.ZERO)!=1 ) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_DOCUMENTOAB_VALOR + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

				if (TSUtil.isEmpty(model.getDataVencimento())) {

					if (TSUtil.isEmpty(model.getCondicaoPagamento()) || (TSUtil.isEmpty(model.getCondicaoPagamento().getId())) || (model.getCondicaoPagamento().getId() == 0)) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_CONDICAO_PAGAMENTO_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

					} else {

						model.getCondicaoPagamento().setEmpresa(model.getEmpresa());
						
						CondicaoPagamento cond = new CondicaoPagamentoDAO().obter(model.getCondicaoPagamento());

						if (TSUtil.isEmpty(cond)) {

							retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_CONDICAO_PAGAMENTO_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

						}else{
							
							if(!TSUtil.isEmpty(model.getParcelas())){
								
								if(TSUtil.isEmpty(cond.getQuantidadeParcelas())){
									
									model.setParcelas(null);
									
								}else{
									
									if(model.getParcelas().size()!=cond.getQuantidadeParcelas()){
										
										retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_QTD_PARCELAS + Constantes.CAMPO_OBRIGATORIO + "\n");
										
									}else{
										
										BigDecimal valor = BigDecimal.ZERO;
										
										for (ParcelaAB p : model.getParcelas()) {
											
											if(TSUtil.isEmpty(p.getDataVencimento()) || (!TSUtil.isEmpty(model.getDataVencimento()) && model.getDataVencimento().before(new Date()) )){
												
												retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_PARCELA_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");
												
											}
											
											if(TSUtil.isEmpty(p.getValor()) || (!TSUtil.isEmpty(p.getValor()) && p.getValor().compareTo(BigDecimal.ZERO)<=0)){
												
												retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_PARCELA_VALOR + Constantes.CAMPO_OBRIGATORIO + "\n");
												
											}else{
												
												valor = valor.add(p.getValor());
												
											}
											
										}
										
										
										if(model.getValor().setScale(2,RoundingMode.HALF_UP).compareTo(valor.setScale(2,RoundingMode.HALF_UP))!=0){
											
											retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_VALOR_PARCELAS + Constantes.CAMPO_OBRIGATORIO + "\n");
											
										}
										
									}
									
								}
								

							}
							
						}
						
					}

				} else {

					model.setCondicaoPagamento(new CondicaoPagamento());

				}

				if (!TSUtil.isEmpty(model.getSequencia())) {

					model.getSequencia().setEmpresa(model.getEmpresa());

					if (TSUtil.isEmpty(new SequenciaDAO().obter(model.getSequencia()))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_SEQUENCIA + "\n");

					}

				} else {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_SEQUENCIA + "\n");

				}

				if (TSUtil.isEmpty(model.getPedidoVenda())) {

					model.setPedidoVenda(new PedidoVenda());

				}

			}			
			
		}

		return retorno.toString();

	}


}
