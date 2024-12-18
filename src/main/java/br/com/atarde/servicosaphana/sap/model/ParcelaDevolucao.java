/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.sap.model;

import java.io.Serializable;

/**
 * 
 * @author mroland
 */
@SuppressWarnings("serial")
public class ParcelaDevolucao extends ParcelaAB implements Serializable {

	private Devolucao devolucao;

	public Devolucao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

}
