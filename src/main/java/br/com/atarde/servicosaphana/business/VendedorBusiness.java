package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.sap.business.service.VendedorSapBusinessService;
import br.com.atarde.servicosaphana.sap.dao.VendedorDAO;
import br.com.atarde.servicosaphana.sap.model.Vendedor;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class VendedorBusiness {

	public Vendedor validar(Vendedor model) throws Exception {

		if (TSUtil.isEmpty(Utilitarios.tratarLong(model.getId()))) {
			
			Vendedor vendedor = new VendedorDAO().obterPeloIdentificador(model);

			if (TSUtil.isEmpty(vendedor)) {

				if (TSUtil.isEmpty(new VendedorDAO().obterPeloNome(model))) {

					model = new VendedorSapBusinessService().inserir(model);

				} else {

					model.setMensagemErro("Erro: Vendedor nome=" + model.getNome().toString() + " igual da base mas de id(" + model.getId().toString() + ") diferente");

					throw new TSApplicationException("Erro: Vendedor nome=" + model.getNome().toString() + " igual da base mas de id(" + model.getId().toString() + ") diferente");

				}

			}else{
				
				model.setId(vendedor.getId());
				
			}

		}

		return model;

	}

}
