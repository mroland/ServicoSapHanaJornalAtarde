/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import br.com.atarde.servicosaphana.sap.model.CentroCusto;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class CentroCustoDiApiDAO {

	private Empresa empresa;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			//this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}

	public void inserir(CentroCusto model) throws TSApplicationException {

	

	}


}
