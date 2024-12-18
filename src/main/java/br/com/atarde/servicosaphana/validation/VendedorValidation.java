package br.com.atarde.servicosaphana.validation;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.dao.GrupoComissaoDAO;
import br.com.atarde.servicosaphana.sap.dao.VendedorDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.GrupoComissao;
import br.com.atarde.servicosaphana.sap.model.Vendedor;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public class VendedorValidation {

	public String validar(Vendedor model) {

		StringBuilder retorno = new StringBuilder();
		
		if(!TSUtil.isEmpty(model)){
					
			if(TSUtil.isEmpty(new VendedorDAO().obter(model))){
				
				retorno.append(this.validaPessoa(model));

				if (TSUtil.isEmpty(model.getGrupoComissao()) || (TSUtil.isEmpty(model.getGrupoComissao().getId()))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR_GRUPO_COMISSAO + Constantes.CAMPO_OBRIGATORIO + "\n");

				} else {

					model.getGrupoComissao().setEmpresa(model.getEmpresa());

					if (TSUtil.isEmpty(new GrupoComissaoDAO().obter(model.getGrupoComissao()))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR_GRUPO_COMISSAO + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

				}		
				
			}else{
				
				model.setGrupoComissao(new GrupoComissao());
				
			}

		}else{
			
			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR  + "\n");
			
		}
			
		
		return retorno.toString();

	}

	private String validaPessoa(Vendedor model) {

		StringBuilder retorno = new StringBuilder();
		
		if ((TSUtil.isEmpty(model.getTipoIdentificador()))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR_TIPO_IDENTIFICADOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getIdentificador()) || (!TSUtil.isEmpty(model.getIdentificador()) && model.getIdentificador().length() > 20)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR_IDENTIFICADOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setIdentificador(model.getIdentificador().replace(".", "").replace("/", "").replace("-", ""));
		}

		if (TSUtil.isEmpty(model.getNome())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR_NOME + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		return retorno.toString();
	}

	public String validar(Vendedor model, Empresa empresa) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR + "\n");

		} else {

			model.setEmpresa(empresa);

			retorno.append(this.validar(model));
		}

		return retorno.toString();

	}

	public void validarPesquisa(Vendedor model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_VENDEDOR + "\n");

		} else {
			
			if(TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))){
				
				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO);
				
			}else{
				
				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));
				
			}
			
		}
		
		if(!TSUtil.isEmpty(retorno.toString())){
			
			model.setMensagemErro(retorno.toString());
			
		}
		
		
	}

}
