/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class HistoricoTransferenciaEstoqueLinha extends TransferenciaEstoqueLinha implements Serializable {

	public HistoricoTransferenciaEstoqueLinha(TransferenciaEstoque model) {
		this.setTransferenciaEstoque(model);
	}

	public HistoricoTransferenciaEstoqueLinha() {

	}

}
