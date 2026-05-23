package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class PedidoVendaDAO {
	
	public PedidoVenda obterIdExterno(PedidoVendaAB model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT ORDR.\"DocEntry\" FROM " + model.getEmpresa().getDbInstancia() + ".ORDR WHERE ORDR.\"NumAtCard\" = ? AND ORDR.\"U_ATRD_Origem\"  = ? AND NOT EXISTS (SELECT 1 FROM " + model.getEmpresa().getDbInstancia() + ".ORDR C, " + model.getEmpresa().getDbInstancia() + ".RDR1 L WHERE C.\"DocEntry\" = L.\"DocEntry\" AND L.\"BaseEntry\" = ORDR.\"DocEntry\" AND C.CANCELED = 'Y') AND ORDR.CANCELED ='N' ", model.getIdExterno(), model.getOrigem().getId());
		
		return (PedidoVenda) broker.getObjectBean(PedidoVenda.class, "id");
		
	}	

}
