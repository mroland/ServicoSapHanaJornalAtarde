package br.com.atarde.servicosaphana.validation;

import java.math.BigDecimal;
import java.util.Date;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.dao.CodigoInternoDAO;
import br.com.atarde.servicosaphana.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public class ExtratoBancarioValidation {

	public String validar(ExtratoBancario model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

			retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
			
			if (!TSUtil.isEmpty(model.getConta()) && (model.getConta().length() > 50)) {

				retorno.append(Constantes.OBJETO_EXTRATO_BANCARIO_CONTA + "\n");

			}				

			if (TSUtil.isEmpty(model.getDataVencimento())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_EXTRATO_BANCARIO_DATA_VENCIMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}
			
			if (TSUtil.isEmpty(model.getDetalhe()) || (!TSUtil.isEmpty(model.getDetalhe()) && (model.getDetalhe().length() > 20))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_EXTRATO_BANCARIO_DETALHE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}	
			
			if (!TSUtil.isEmpty(model.getNumeroDocumento()) && (model.getNumeroDocumento().length() > 10)) {

				retorno.append(Constantes.OBJETO_EXTRATO_BANCARIO_NUMERO_DOCUMENTO + "\n");

			}
			
			if(TSUtil.isEmpty(model.getValorDebito()) && TSUtil.isEmpty(model.getValorCredito()) || (!TSUtil.isEmpty(model.getValorDebito()) && model.getValorDebito().equals(BigDecimal.ZERO) && !TSUtil.isEmpty(model.getValorCredito()) && model.getValorCredito().equals(BigDecimal.ZERO))){
				
				retorno.append(Constantes.OBJETO_EXTRATO_BANCARIO_DEBITO_CREDITO_ZERO + "\n");
				
			}	
			
			if (TSUtil.isEmpty(model.getContaContabil())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}else{
				
				model.getContaContabil().setEmpresa(model.getEmpresa());
				
				if(TSUtil.isEmpty(new ContaContabilDAO().obter(model.getContaContabil()))){
					
					retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");
					
				}
				
			}
			
			if (TSUtil.isEmpty(model.getCodigoInterno())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}else{
				
				model.getCodigoInterno().setEmpresa(model.getEmpresa());
				
				if(TSUtil.isEmpty(new CodigoInternoDAO().obter(model.getCodigoInterno()))){
					
					retorno.append(Constantes.OBJETO_OBRIGATORIO_CODIGO_INTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");
					
				}
				
			}			

			if (TSUtil.isEmpty(model.getDataExportacao())) {

				model.setDataExportacao(new Date(System.currentTimeMillis()));

			}
			
			if (TSUtil.isEmpty(model.getStatus())) {

				model.setStatus(new Status(Constantes.STATUS_NAO_PROCESSADO));

			}


		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

}
