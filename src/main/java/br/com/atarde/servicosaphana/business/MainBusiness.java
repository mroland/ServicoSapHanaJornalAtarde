package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;

public abstract class MainBusiness<T> {

	public T insertBatchEvent(T model) throws TSApplicationException{

		String retorno;

		retorno = this.validar(model);

		if ("".equals(retorno)) {

			return this.inserirLote(model);

		} else {
			
			return model;

		}

	}

	public T insertEvent(T model) throws TSApplicationException {

		String retorno;

		retorno = this.validar(model);

		if ("".equals(retorno)) {

			return this.inserir(model);

		} else {

			return model;

		}

	}
	
	public abstract T inserir(T model) ;

	public abstract String validar(T model);
	
	public abstract T inserirLote(T model) throws TSApplicationException;
	
	public abstract void inserirSAP(Empresa empresa) ;
	
	public abstract void alterarStatusInterface()  throws TSApplicationException ;

}
