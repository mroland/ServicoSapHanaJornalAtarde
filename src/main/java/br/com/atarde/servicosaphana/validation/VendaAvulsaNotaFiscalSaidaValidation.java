package br.com.atarde.servicosaphana.validation;

import br.com.atarde.servicosaphana.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosaphana.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosaphana.sap.model.CFOP;
import br.com.atarde.servicosaphana.sap.model.CST;
import br.com.atarde.servicosaphana.sap.model.CodigoImposto;
import br.com.atarde.servicosaphana.sap.model.Filial;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinha;
import br.com.atarde.servicosaphana.sap.model.Utilizacao;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class VendaAvulsaNotaFiscalSaidaValidation extends NotaFiscalSaidaValidation {

	public String validar(VendaAvulsaNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validar(model));

		if (TSUtil.isEmpty(retorno.toString())) {

			retorno.append(this.validaNFF(model));

		} 

		return retorno.toString();

	}

	public String validaNFF(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(retorno.toString())) {

			VendaAvulsaNotaFiscalSaida nota = (VendaAvulsaNotaFiscalSaida) model;
			
			if(!TSUtil.isEmpty(new VendaAvulsaNotaFiscalSaidaDAO().obterIdExternoInterface(nota))) {
				
				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");
				
				return retorno.toString();
				
			}	
			
			if(TSUtil.isEmpty(Utilitarios.tratarString(nota.getCliente().getId()))){
				
				if ((nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0) && (TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) || (nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0 && (!TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual())) && nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual().length()>15 )) ) {				

					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL + Constantes.CAMPO_OBRIGATORIO + "\n");

				}
				
			}

			if (TSUtil.isEmpty(nota.getObservacao()) || (!TSUtil.isEmpty(nota.getObservacao()) &&  nota.getObservacao().length()>500)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}else {
				
				nota.setObservacao(nota.getObservacao().toUpperCase());
				
			}

            if ((nota.getSequencia().getId() == 28) && (TSUtil.isEmpty(nota.getUObservacao()) || (!TSUtil.isEmpty(nota.getUObservacao()) && nota.getUObservacao().length()>500))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

            }
            
            if (TSUtil.isEmpty(nota.getUBanca()) || (!TSUtil.isEmpty(nota.getUBanca()) && nota.getUBanca().length()>100)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_BANCA + Constantes.CAMPO_OBRIGATORIO + "\n");

            }else {
            	
            	nota.setUBanca(nota.getUBanca().toUpperCase());
            	
            }

            if (TSUtil.isEmpty(nota.getULote()) || (!TSUtil.isEmpty(nota.getULote()) && nota.getULote().length()>50)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_LOTE + Constantes.CAMPO_OBRIGATORIO + "\n");

            }

            if (TSUtil.isEmpty(nota.getUTipoBanca()) || (!TSUtil.isEmpty(nota.getUTipoBanca()) && nota.getUTipoBanca().length()>50)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOBANCA + Constantes.CAMPO_OBRIGATORIO + "\n");
            }else {
            	
            	nota.setUTipoBanca(nota.getUTipoBanca().toUpperCase());
            	
            }

            if (TSUtil.isEmpty(nota.getUTipoFaturamento()) || (!TSUtil.isEmpty(nota.getUTipoFaturamento()) && nota.getUTipoFaturamento().length()>50)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOFATURAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

            }else {
            	
            	nota.setUTipoFaturamento(nota.getUTipoFaturamento().toUpperCase());
            }
            
            if (TSUtil.isEmpty(nota.getUEnderecoEntrega()) || (!TSUtil.isEmpty(nota.getUEnderecoEntrega()) && nota.getUEnderecoEntrega().length()>254)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA + Constantes.CAMPO_OBRIGATORIO + "\n");

            }else {
            	
            	nota.setUEnderecoEntrega(nota.getUEnderecoEntrega().toUpperCase());
            	
            }
            
            if (TSUtil.isEmpty(nota.getUObservacao()) || (!TSUtil.isEmpty(nota.getUObservacao()) && nota.getUObservacao().length()>254)) {

                retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

            }else {
            	
            	nota.setUObservacao(nota.getUObservacao().toUpperCase());
            }
            
			if (!TSUtil.isEmpty(nota.getLinhas()) || (!TSUtil.isEmpty(nota.getLinhas()) && nota.getLinhas().size()!=0)) {

				for (VendaAvulsaNotaFiscalSaidaLinha linha : nota.getLinhas()) {

					linha.setEmpresa(model.getEmpresa());
					
					//colocar fixo para que nao impacte os sistemas legados de implementar
					linha.setCodigoImposto(new CodigoImposto());
										
					if(model.getSequencia().getId().equals(26L)){
						
						linha.setUtilizacao(new Utilizacao(16L, model.getEmpresa()));
						
						linha.getCodigoImposto().setId("5101-007");
						
					}else{
						
						if(!TSUtil.isEmpty(linha.getFlagImposto()) && linha.getFlagImposto()){
							
							linha.setUtilizacao(new Utilizacao(21L, model.getEmpresa()));
							
						}else{
							
							linha.setUtilizacao(new Utilizacao(16L, model.getEmpresa()));
							
						}
											
						linha.getCodigoImposto().setId("5101-006");
						
					}
					
					linha.setCfop(new CFOP());
					
					linha.setCstIPI(new CST());
					
					linha.setCstPIS(new CST());
					
					linha.setCstCOFINS(new CST());
					
					linha.setCstICMS(new CST());

					retorno.append(this.validaLinhaNFF(linha, model.getFilial()));

				}

			}else{
				
				retorno.append(Constantes.OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA + "\n");
				
			}
			
			if (!TSUtil.isEmpty(nota.getRomaneios()) || (!TSUtil.isEmpty(nota.getRomaneios()) && nota.getRomaneios().size()!=0)) {

				for (VendaAvulsaNotaFiscalSaidaRomaneio romaneio : nota.getRomaneios()) {

					romaneio.setEmpresa(model.getEmpresa());

					retorno.append(this.validaRomaneio(romaneio));

				}

			}			

		}

		return retorno.toString();

	}

	private String validaRomaneio(VendaAvulsaNotaFiscalSaidaRomaneio model) {
		
		StringBuilder retorno = new StringBuilder();
		
		if(TSUtil.isEmpty(model.getIdExterno()) || (!TSUtil.isEmpty(model.getIdExterno()) && model.getIdExterno().length()>100)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ID_EXTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			model.setIdExterno(model.getIdExterno().trim());
			
		}
		
		if(TSUtil.isEmpty(model.getRoteiro()) || (!TSUtil.isEmpty(model.getRoteiro()) && model.getRoteiro().length()>50)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ROTEIRO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			model.setRoteiro(model.getRoteiro().trim());
			
		}

		if(TSUtil.isEmpty(model.getDescricao()) || (!TSUtil.isEmpty(model.getDescricao()) && model.getDescricao().length()>100)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_DESCRICAO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			model.setDescricao(model.getDescricao().trim());
			
		}
		
		if(TSUtil.isEmpty(model.getData())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_DATA + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}			
		
		if(TSUtil.isEmpty(model.getReparte())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_REPARTE + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		if(TSUtil.isEmpty(model.getEncalhe())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_ENCALHE + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		if(TSUtil.isEmpty(model.getVenda())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_VENDA + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}			
		
		if(TSUtil.isEmpty(model.getPreco())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_PRECO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}	
		
		if(TSUtil.isEmpty(model.getValor())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_VALOR + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		if(TSUtil.isEmpty(model.getRdj()) || (!TSUtil.isEmpty(model.getRdj()) && model.getRdj().length()>50)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_RDJ + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			model.setRdj(model.getRdj().trim());
			
		}
		
		if(TSUtil.isEmpty(model.getRegiao()) || (!TSUtil.isEmpty(model.getRegiao()) && model.getRegiao().length()>1)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_REGIAO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		if(TSUtil.isEmpty(model.getCidade()) || (!TSUtil.isEmpty(model.getCidade()) && model.getCidade().length()>50)) {
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_ROMANEIO_CIDADE + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}else{
			
			model.setCidade(model.getCidade().trim());
			
		}
		
		return retorno.toString();
		
	}

	protected String validaLinhaNFF(VendaAvulsaNotaFiscalSaidaLinha model, Filial filial) {		

		StringBuilder retorno = new StringBuilder();
		
		retorno.append(super.validaLinhaNFF(model, filial));
		
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
		
		if(TSUtil.isEmpty(model.getFlagImposto())){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_FLAG_IMPOSTO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}

/*		
		if (TSUtil.isEmpty(model.getCfop()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getCfop().getCodigo())))) {

			retorno.append(Constantes.CFOP_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getCfop().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new CfopDAO().obterPeloCodigo(model.getCfop()))) {

				retorno.append(Constantes.CFOP_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}
		
        if (TSUtil.isEmpty(model.getCstICMS()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getCstICMS().getCodigo())))) {

            retorno.append(Constantes.CSTICMS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

        } else {
        	
        	categoria = new Categoria("codigo", "ICMS");
        	
        	categoria.setEmpresa(model.getEmpresa());

            model.getCstICMS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));
            
            model.getCstICMS().setEmpresa(model.getEmpresa());

            if (TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstICMS()))) {

                retorno.append(Constantes.CSTICMS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

            }

        }
        
        if (TSUtil.isEmpty(model.getCstCOFINS()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getCstCOFINS().getCodigo())))) {

            retorno.append(Constantes.CSTCOFINS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

        } else {
        	
        	categoria = new Categoria("codigo", "COFINS");
        	
        	categoria.setEmpresa(model.getEmpresa());        	

            model.getCstCOFINS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));
            
            model.getCstCOFINS().setEmpresa(model.getEmpresa());

            if (TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstCOFINS()))) {

                retorno.append(Constantes.CSTCOFINS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

            }

        }

        if (TSUtil.isEmpty(model.getCstIPI()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getCstIPI().getCodigo())))) {

            retorno.append(Constantes.CSTIPI_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

        } else {
        	
        	categoria = new Categoria("codigo", "IPI");
        	
        	categoria.setEmpresa(model.getEmpresa());    

            model.getCstIPI().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));
            
            model.getCstIPI().setEmpresa(model.getEmpresa());
            
            if (TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstIPI()))) {

                retorno.append(Constantes.CSTIPI_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

            }

        }

        if (TSUtil.isEmpty(model.getCstPIS()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getCstPIS().getCodigo())))) {

            retorno.append(Constantes.CSTPIS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

        } else {
        	
        	categoria = new Categoria("codigo", "PIS");
        	
        	categoria.setEmpresa(model.getEmpresa());    

            model.getCstPIS().setCategoria(new CategoriaDAO().obterPeloCodigo(categoria));
            
            model.getCstPIS().setEmpresa(model.getEmpresa());        	

            if (TSUtil.isEmpty(new CstDAO().obterPeloCodigo(model.getCstPIS()))) {

                retorno.append(Constantes.CSTPIS_INEXISTENTE + Constantes.CAMPO_OBRIGATORIO + "\n");

            }

        }
        */
        if (TSUtil.isEmpty(model.getContaContabil()) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getContaContabil().getId())))) {

    		retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

        }else{
        	
        	model.getContaContabil().setEmpresa(model.getEmpresa());
        	
        	if(TSUtil.isEmpty(new ContaContabilDAO().obter(model.getContaContabil()))){
        		
        		retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");
        	
        	}
        	
        	
        }       
		
		return retorno.toString();
		
	}

}
