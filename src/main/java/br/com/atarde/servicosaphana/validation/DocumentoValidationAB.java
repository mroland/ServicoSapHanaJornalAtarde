package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import br.com.atarde.servicosaphana.sap.dao.CodigoImpostoDAO;
import br.com.atarde.servicosaphana.sap.dao.EstoqueDAO;
import br.com.atarde.servicosaphana.sap.dao.ItemDAO;
import br.com.atarde.servicosaphana.sap.dao.UtilizacaoDAO;
import br.com.atarde.servicosaphana.sap.model.CodigoImposto;
import br.com.atarde.servicosaphana.sap.model.DocumentoAB;
import br.com.atarde.servicosaphana.sap.model.DocumentoLinhaAB;
import br.com.atarde.servicosaphana.sap.model.Estoque;
import br.com.atarde.servicosaphana.sap.model.Utilizacao;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class DocumentoValidationAB {

	public String validar(DocumentoAB model) {
		
        StringBuilder retorno = new StringBuilder();

        if (TSUtil.isEmpty(model.getDataLancamento())) {

            retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_LANCAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

        }

        if (TSUtil.isEmpty(model.getDataDocumento())) {

            retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_DOCUMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

        }

        if (TSUtil.isEmpty(model.getDataCriacao())) {

            retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_CRIACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

        }

        if (TSUtil.isEmpty(model.getDataExportacao())) {

			model.setDataExportacao(new Timestamp(System.currentTimeMillis()));

        }


        return retorno.toString();

	}

	protected String validaLinhaNFF(DocumentoLinhaAB model) {
		
		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getItem()) || (TSUtil.isEmpty(model.getItem().getId()))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {
			
			model.getItem().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new ItemDAO().obter(model.getItem()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM + Constantes.CAMPO_OBRIGATORIO + "\n");

			}else{
				
				if(TSUtil.isEmpty(model.getItem().getEstoque())){
					
					model.getItem().setEstoque(new Estoque());
					
				}else{
					
					if(TSUtil.isEmpty(new EstoqueDAO().obter(new Estoque(model.getItem(), model.getEmpresa())))){
						
						retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE + "\n");
						
					}
				}
			}
		}

		if (TSUtil.isEmpty(model.getQuantidade()) || (model.getQuantidade() == 0)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_QUANTIDADE + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getValor()) || model.getValor().setScale(2,RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP))!=1) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_VALOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		}
		
		if(TSUtil.isEmpty(model.getUtilizacao()) || TSUtil.isEmpty(Utilitarios.tratarLong(model.getUtilizacao().getId())) || (!TSUtil.isEmpty(Utilitarios.tratarLong(model.getUtilizacao().getId())) && TSUtil.isEmpty(new UtilizacaoDAO().obter(new Utilizacao(model.getUtilizacao().getId(), model.getEmpresa()))))){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_UTILIZACAO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		if(TSUtil.isEmpty(model.getCodigoImposto()) || TSUtil.isEmpty(Utilitarios.tratarString(model.getCodigoImposto().getId())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getCodigoImposto().getId())) && TSUtil.isEmpty(new CodigoImpostoDAO().obter(new CodigoImposto("id",model.getCodigoImposto().getId(), model.getEmpresa()))))){
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_CODIGO_IMPOSTO + Constantes.CAMPO_OBRIGATORIO + "\n");
			
		}
		
		return retorno.toString();
	}

}
