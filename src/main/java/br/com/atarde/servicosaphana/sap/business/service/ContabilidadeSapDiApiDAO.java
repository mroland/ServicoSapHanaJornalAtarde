package br.com.atarde.servicosaphana.sap.business.service;

import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ContabilidadeSapDiApiDAO {

	
	private Empresa empresa;
	private int retorno;  	

    private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			//this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}
		
    }

	public void inserir(Contabilidade model) throws TSApplicationException {
		



	}
	
}
