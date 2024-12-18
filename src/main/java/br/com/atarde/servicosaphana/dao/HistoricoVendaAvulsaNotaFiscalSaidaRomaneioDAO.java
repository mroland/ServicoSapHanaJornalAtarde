package br.com.atarde.servicosaphana.dao;

import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoVendaAvulsaNotaFiscalSaidaRomaneioDAO {

	public void inserirInterface(VendaAvulsaNotaFiscalSaidaRomaneio model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_vendaavulsanotafiscalsaidaromaneios_id_seq"));

		broker.setSQL("INSERT INTO HISTORICO_VENDAAVULSANOTAFISCALSAIDAROMANEIOS(ID, ID_EXTERNO, ROTEIRO, DESCRICAO, DATA, REPARTE, ENCALHE, VENDA, PRECO, VALOR, RDJ, EMPRESA_ID, HISTORICO_NOTAFISCALSAIDA_ID, SAP_NOTAFISCALSAIDA_ID, REGIAO, CIDADE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(),model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getInterfaceId(), model.getNota().getId(), model.getRegiao(), model.getCidade());

		broker.execute();
		
	}


}
