/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.business.service;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ExtratoBancarioSap3DiApiDAO {

	private Empresa empresa;
	private int retorno;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			//this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}

	public void inserir(ExtratoBancario model) throws TSApplicationException {

		

	}

}
