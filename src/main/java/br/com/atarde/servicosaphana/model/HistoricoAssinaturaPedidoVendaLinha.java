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
public class HistoricoAssinaturaPedidoVendaLinha extends AssinaturaPedidoVendaLinha implements Serializable{

    public HistoricoAssinaturaPedidoVendaLinha(AssinaturaPedidoVenda nota) {
        this.setPedidoVenda(nota);
    }

    public HistoricoAssinaturaPedidoVendaLinha(){
        
    }


}
