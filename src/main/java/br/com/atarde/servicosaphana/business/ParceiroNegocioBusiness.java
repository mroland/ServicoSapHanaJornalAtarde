/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.sap.business.service.ParceiroNegocioSapBusinessService;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioEnderecoDAO;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ParceiroNegocioBusiness {

	public ParceiroNegocio validarClienteComEndereco(ParceiroNegocio model) throws Exception {
		
		ParceiroNegocio parceiroNegocio;

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getId()))) {

			parceiroNegocio = new ParceiroNegocio();

			parceiroNegocio.setId(model.getId());

		} else {

			parceiroNegocio = new ParceiroNegocioDAO().obterPorIdentificador(model);

		}

		if (TSUtil.isEmpty(parceiroNegocio)) {

			new ParceiroNegocioSapBusinessService().inserirComEndereco(model);

		} else {
			
			model.setId(parceiroNegocio.getId());
			
			if(TSUtil.isEmpty(parceiroNegocio.getEnderecoEntregaDefault())){
				
				if(model.getFlagEndereco()){
								
					this.inserirEnderecoEntrega(model);
					
				}
				
			}else{
				
				model.setEnderecoEntregaDefault(parceiroNegocio.getEnderecoEntregaDefault());
				
			}
			
			if(TSUtil.isEmpty(parceiroNegocio.getEnderecoCobrancaDefault())){
				
				if(model.getFlagEndereco()){
					
					this.inserirEnderecoCobranca(model);
					
				}
				
			}else{
				
				model.setEnderecoCobrancaDefault(parceiroNegocio.getEnderecoCobrancaDefault());
				
			}			
			
		}

		return model;

	}

	private void inserirEnderecoCobranca(ParceiroNegocio model) throws TSApplicationException {

		ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

		endereco.setParceiroNegocio(new ParceiroNegocio("id", model.getId()));

		endereco.setEmpresa(model.getEmpresa());

		endereco.setTipoEndereco("B");
		
		String enderecoCobranca = "Cobranca" + /*" - " + model.getId() + */ "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero();

		endereco.setId(enderecoCobranca.replace(" ", ""));

		endereco = new ParceiroNegocioEnderecoDAO().obter(endereco);

		if (TSUtil.isEmpty(endereco) && TSUtil.isEmpty(model.getEnderecoCobrancaDefault())) {

			model.getEndereco().setTipoEndereco("B");

			model.getEndereco().setId(enderecoCobranca.replace(" ", ""));

			new ParceiroNegocioSapBusinessService().inserirEnderecoCliente(model);

		}
		
		model.setEnderecoCobrancaDefault(enderecoCobranca.replace(" ", ""));
		
	}

	private void inserirEnderecoEntrega(ParceiroNegocio model) throws TSApplicationException {

		ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

		endereco.setParceiroNegocio(new ParceiroNegocio("id", model.getId()));

		endereco.setEmpresa(model.getEmpresa());

		endereco.setTipoEndereco("S");
		
		String enderecoEntrega = "Entrega" + /* " - " + model.getId() + */ "."  + model.getEndereco().getCep() + "." + model.getEndereco().getNumero();

		endereco.setId(enderecoEntrega.replace(" ", ""));

		endereco = new ParceiroNegocioEnderecoDAO().obter(endereco);

		if (TSUtil.isEmpty(endereco) && TSUtil.isEmpty(model.getEnderecoEntregaDefault())) {

			model.getEndereco().setTipoEndereco("S");

			model.getEndereco().setId(enderecoEntrega.replace(" ", ""));

			new ParceiroNegocioSapBusinessService().inserirEnderecoCliente(model);

		}
		
		model.setEnderecoEntregaDefault(enderecoEntrega.replace(" ", ""));		

		
	}

	protected ParceiroNegocio validarClienteSemEndereco(ParceiroNegocio model) throws Exception {

		ParceiroNegocio parceiroNegocioModel = new ParceiroNegocioDAO().obterPorIdentificador(model);

		if (TSUtil.isEmpty(parceiroNegocioModel)) {

			model = new ParceiroNegocioSapBusinessService().inserirSemEndereco(model);

		}

		return model;

	}

}
