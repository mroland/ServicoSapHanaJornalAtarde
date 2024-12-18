package br.com.atarde.servicosaphana.sap.business.service;

import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ContasReceberSapDiApiDAO {

	private Empresa empresa;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			//this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}    

    public void inserirTransferencia(ContasReceber model) throws TSApplicationException {

   

    }

    public void inserirBoleto(ContasReceber model) throws TSApplicationException {

   

    }

    public void cancelar(ContasReceber model) throws TSApplicationException {

     


    }

    public void cancelarBoleto(ContasReceber model) throws TSApplicationException {

    

    }
    
	private int salvarBoleto()  {

		return 0;
	}    

}
