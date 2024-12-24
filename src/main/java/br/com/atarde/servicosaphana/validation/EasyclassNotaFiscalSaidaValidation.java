package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.atarde.servicosaphana.dao.EasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.sap.dao.CategoriaDAO;
import br.com.atarde.servicosaphana.sap.dao.CstDAO;
import br.com.atarde.servicosaphana.sap.model.CST;
import br.com.atarde.servicosaphana.sap.model.Categoria;
import br.com.atarde.servicosaphana.sap.model.Filial;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinha;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class EasyclassNotaFiscalSaidaValidation extends NotaFiscalSaidaValidation {

	public String validar(EasyclassNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validar(model));

		if (TSUtil.isEmpty(retorno.toString())) {

			if (!TSUtil.isEmpty(model.getAnunciante())) {

				model.getAnunciante().setEmpresa(model.getEmpresa());

			}
			
			retorno.append(new ParceiroNegocioValidation().validar(model.getAnunciante()));
			
			retorno.append(this.validaNFF(model));

		}

		return retorno.toString();

	}

	public String validaNFF(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(retorno.toString())) {

			EasyclassNotaFiscalSaida nota = (EasyclassNotaFiscalSaida) model;
			
			if(!TSUtil.isEmpty(new EasyclassNotaFiscalSaidaDAO().obterIdExternoInterface(nota))) {
				
				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");
				
				return retorno.toString();
				
			}

			if (TSUtil.isEmpty(model.getUValorBruto()) || (model.getUValorBruto().equals(BigDecimal.ZERO))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_VALOR_BRUTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUComissaoAgencia())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_COMISSAO_AGENCIA + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUDataPublicacaoFinal())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_U_DATA_PUBLICACAO_FINAL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUDataPublicacaoInicial())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_U_DATA_PUBLICACAO_INICIAL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUDiasPublicacao()) || (!TSUtil.isEmpty(nota.getUDiasPublicacao()) && nota.getUDiasPublicacao().length() > 100)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_U_DIAS_PUBLICACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUTituloPublicacao()) || (!TSUtil.isEmpty(nota.getUTituloPublicacao()) && nota.getUTituloPublicacao().length() > 254)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_U_TITULO_PUBLICACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}else {
				
				nota.setUTituloPublicacao(nota.getUTituloPublicacao().toUpperCase());
				
			}
			
            if (TSUtil.isEmpty(nota.getUEnderecoEntrega()) || (!TSUtil.isEmpty(nota.getUEnderecoEntrega()) && nota.getUEnderecoEntrega().length()>254)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA + Constantes.CAMPO_OBRIGATORIO + "\n");

            }else {
            	
            	nota.setUEnderecoEntrega(nota.getUEnderecoEntrega().toUpperCase());
            }

            if (!TSUtil.isEmpty(nota.getUNumeroPI()) && nota.getUNumeroPI().length()>32) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_NUMERO_PI + "\n");

            }
            
            if (!TSUtil.isEmpty(nota.getUTipoTransacao()) && nota.getUTipoTransacao().length()>10) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_TIPO_TRANSACAO + "\n");

            }    
            
            if (!TSUtil.isEmpty(nota.getUPeriodo()) && nota.getUPeriodo().length()>30) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PERIODO + "\n");

            }  
            
            if (!TSUtil.isEmpty(nota.getUFormato()) && nota.getUFormato().length()>50) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_FORMATO + "\n");

            }  
            
            if (!TSUtil.isEmpty(nota.getUPageViews()) && nota.getUPageViews().equals(BigDecimal.ZERO)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PAGEVIEWS + "\n");

            } 
            
            if (!TSUtil.isEmpty(nota.getUEntregaVendedor())) {
            	
            	if(nota.getUEntregaVendedor().length()>254) {
            		
            		retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENTREGA_VENDEDOR + "\n");

            	}else {
            		
            		nota.setUEntregaVendedor(nota.getUEntregaVendedor().toUpperCase());
            		
            	}


            }  
            
            if (!TSUtil.isEmpty(nota.getUProduto()) && nota.getUProduto().length()>254) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_PRODUTO + "\n");

            }  
            
            if (!TSUtil.isEmpty(nota.getUCampanha())) {
            	
            	if(nota.getUCampanha().length()>254) {
            		
            		retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_CAMPANHA + "\n");
            		
            	}else {
            		
            		nota.setUCampanha(nota.getUCampanha().toUpperCase());
            		
            	}


            }  
            
            if (!TSUtil.isEmpty(nota.getUPostoId()) && nota.getUPostoId().length()>64) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_POSTO_ID + "\n");

            }             
            
			if (!TSUtil.isEmpty(nota.getLinhas()) || (!TSUtil.isEmpty(nota.getLinhas()) && nota.getLinhas().size() != 0)) {

				for (EasyclassNotaFiscalSaidaLinha linha : nota.getLinhas()) {

					linha.setEmpresa(model.getEmpresa());

					retorno.append(this.validaLinhaNFF(linha, model.getFilial()));

				}

			} else {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA + "\n");

			}

		}

		return retorno.toString();

	}

	protected String validaLinhaNFF(EasyclassNotaFiscalSaidaLinha model, Filial filial) {

		Categoria categoria;

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validaLinhaNFF(model, filial));

		if (TSUtil.isEmpty(model.getUCmXCol()) || (!TSUtil.isEmpty(model.getUCmXCol()) && model.getUCmXCol().length() > 10)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_CMXCOL + Constantes.CAMPO_OBRIGATORIO + "\n");

		}
		
		if (TSUtil.isEmpty(model.getUArea()) || model.getUArea().setScale(2,RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP))!=1) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_AREA + Constantes.CAMPO_OBRIGATORIO + "\n");

		}		

		
		if (TSUtil.isEmpty(model.getUValorUnitario()) || model.getUValorUnitario().setScale(2,RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP))!=1) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_VALOR_UNITARIO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getUTotalCmXCol()) || model.getUTotalCmXCol().setScale(2,RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP))!=1) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_TOTAL_CMXCOL + Constantes.CAMPO_OBRIGATORIO + "\n");

		}
		
		if (TSUtil.isEmpty(model.getUQuantidadeInsercoes()) || (model.getUQuantidadeInsercoes()<=0)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_U_QUANTIDADE_INSERCOES + Constantes.CAMPO_OBRIGATORIO + "\n");

		}		

		if (!TSUtil.isEmpty(model.getPedidoVendaLinha())) {

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getNumero())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA_LINHA_NUMERO);

			}

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getPedidoVenda()) || (TSUtil.isEmpty(Utilitarios.tratarLong(model.getPedidoVendaLinha().getPedidoVenda().getId())))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA);

			}

		} else {

			model.setPedidoVendaLinha(new PedidoVendaLinha());

			model.getPedidoVendaLinha().setPedidoVenda(new PedidoVenda());

		}

		if (TSUtil.isEmpty(model.getCstICMS())) {

			model.setCstICMS(new CST());

		} else {

			categoria = new Categoria("codigo", "ICMS");

			categoria.setEmpresa(model.getEmpresa());

			model.getCstICMS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));

			model.getCstICMS().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(model.getCstICMS()) || (!TSUtil.isEmpty(model.getCstICMS()) && TSUtil.isEmpty(Utilitarios.tratarString(model.getCstICMS().getCodigo()))) || (!TSUtil.isEmpty(model.getCstICMS()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getCstICMS().getCodigo())) && TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstICMS())))) {

				retorno.append(Constantes.CSTICMS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		if (TSUtil.isEmpty(model.getCstCOFINS())) {

			model.setCstCOFINS(new CST());

		} else {

			categoria = new Categoria("codigo", "COFINS");

			categoria.setEmpresa(model.getEmpresa());

			model.getCstCOFINS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));

			model.getCstCOFINS().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(model.getCstCOFINS()) || (!TSUtil.isEmpty(model.getCstCOFINS()) && TSUtil.isEmpty(Utilitarios.tratarString(model.getCstCOFINS().getCodigo()))) || (!TSUtil.isEmpty(model.getCstCOFINS()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getCstCOFINS().getCodigo())) && TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstCOFINS())))) {

				retorno.append(Constantes.CSTCOFINS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		if (TSUtil.isEmpty(model.getCstIPI())) {

			model.setCstIPI(new CST());

		} else {

			categoria = new Categoria("codigo", "IPI");

			categoria.setEmpresa(model.getEmpresa());

			model.getCstIPI().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));

			model.getCstIPI().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(model.getCstIPI()) || (!TSUtil.isEmpty(model.getCstIPI()) && TSUtil.isEmpty(Utilitarios.tratarString(model.getCstIPI().getCodigo()))) || (!TSUtil.isEmpty(model.getCstIPI()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getCstIPI().getCodigo())) && TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstIPI())))) {

				retorno.append(Constantes.CSTIPI_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		if (TSUtil.isEmpty(model.getCstPIS())) {

			model.setCstPIS(new CST());

		} else {

			categoria = new Categoria("codigo", "PIS");

			categoria.setEmpresa(model.getEmpresa());

			model.getCstPIS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));

			model.getCstPIS().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(model.getCstPIS()) || (!TSUtil.isEmpty(model.getCstPIS()) && TSUtil.isEmpty(Utilitarios.tratarString(model.getCstPIS().getCodigo()))) || (!TSUtil.isEmpty(model.getCstPIS()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getCstPIS().getCodigo())) && TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstPIS())))) {

				retorno.append(Constantes.CSTPIS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		return retorno.toString();

	}

}
