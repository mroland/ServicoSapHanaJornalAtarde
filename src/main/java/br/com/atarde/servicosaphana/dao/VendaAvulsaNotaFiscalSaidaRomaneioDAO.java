package br.com.atarde.servicosaphana.dao;

import java.util.List;

import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class VendaAvulsaNotaFiscalSaidaRomaneioDAO {

	public void inserirInterface(VendaAvulsaNotaFiscalSaidaRomaneio model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("vendaavulsanotafiscalsaidaromaneios_id_seq"));

		broker.setSQL("INSERT INTO VENDAAVULSANOTAFISCALSAIDAROMANEIOS(ID, ID_EXTERNO, ROTEIRO, DESCRICAO, DATA, REPARTE, ENCALHE, VENDA, PRECO, VALOR, RDJ, EMPRESA_ID, NOTAFISCALSAIDA_ID, REGIAO, CIDADE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(),model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getInterfaceId(), model.getRegiao(), model.getCidade());

		broker.execute();
		
	}

	public void inserirInterfaceMSSQL(VendaAvulsaNotaFiscalSaidaRomaneio model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("INSERT INTO INTEGRACAOSISTEMALEGADO.DBO.VENDAAVULSANOTAFISCALSAIDAROMANEIOS(ID_EXTERNO, ROTEIRO, DESCRICAO, DATA, REPARTE, ENCALHE, VENDA, PRECO, VALOR, RDJ, EMPRESA_ID, SAP_NOTAFISCALSAIDA_ID, REGIAO, CIDADE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getId(), model.getRegiao(), model.getCidade());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<VendaAvulsaNotaFiscalSaidaRomaneio> pesquisarInterface(VendaAvulsaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, ID_EXTERNO, ROTEIRO, DESCRICAO, DATA, REPARTE, ENCALHE, VENDA, PRECO, VALOR, RDJ, EMPRESA_ID, NOTAFISCALSAIDA_ID, REGIAO, CIDADE FROM VENDAAVULSANOTAFISCALSAIDAROMANEIOS WHERE NOTAFISCALSAIDA_ID = ?",  model.getInterfaceId());
		
		return broker.getCollectionBean(VendaAvulsaNotaFiscalSaidaRomaneio.class, "interfaceId", "idExterno", "roteiro", "descricao", "data", "reparte", "encalhe", "venda", "preco", "valor", "rdj", "empresa.id", "nota.id", "regiao", "cidade");
				
	}

}
