package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosaphana.dao.ContasReceberDAO;
import br.com.atarde.servicosaphana.dao.ContasReceberLinhaDAO;
import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoContasReceberDAO;
import br.com.atarde.servicosaphana.dao.ModalidadePagamentoBoletoDAO;
import br.com.atarde.servicosaphana.dao.ModalidadePagamentoTransferenciaDAO;
import br.com.atarde.servicosaphana.model.HistoricoContasReceber;
import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.validation.ContasReceberValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class ContasReceberBusiness extends MainBusiness<ContasReceber>{

	@Override
	public ContasReceber inserir(ContasReceber model) {
		
		try {
			
			if(!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())){

				//new ContasReceberSapDiApiDAO().inserirBoleto(model);
				
			}else{
				
				//new ContasReceberSapDiApiDAO().inserirTransferencia(model);
			}
			
			model.setStatus(new Status(1L));

			model.setMensagemErro(null);
			
			if(TSUtil.isEmpty(model.getDataExportacao())){
				model.setDataExportacao(new Timestamp(System.currentTimeMillis()));
			}

			new HistoricoContasReceberDAO().inserirInterface(this.carregaHistorico(model));

			new ContasReceberDAO().excluirInterface(model);			
			
		} catch (TSApplicationException e) {
		
			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}	
			
			try {

				new HistoricoContasReceberDAO().inserirInterface(this.carregaHistorico(model));

				new ContasReceberDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}
			
						
		}
				
		return model;
		
	}

	private HistoricoContasReceber carregaHistorico(ContasReceber model) {
		
		HistoricoContasReceber historico = new HistoricoContasReceber();
		
		historico.setCliente(model.getCliente());
		
		historico.setDataDocumento(model.getDataDocumento());
		
		historico.setDataLancamento(model.getDataLancamento());
		
		historico.setDataVencimento(model.getDataVencimento());
		
		historico.setEmpresa(model.getEmpresa());
		
		historico.setLinha(model.getLinha());
		
		historico.setLinhas(model.getLinhas());
		
		historico.setMensagemErro(model.getMensagemErro());
		
		historico.setModalidadePagamentoBoleto(model.getModalidadePagamentoBoleto());
		
		historico.setModalidadePagamentoTransferencia(model.getModalidadePagamentoTransferencia());
		
		historico.setStatus(model.getStatus());
		
		historico.setTipoModalidade(model.getTipoModalidade());
		
		historico.setValor(model.getValor());
		
		historico.setDataImportacao(model.getDataImportacao());
		
		historico.setDataExportacao(model.getDataExportacao());
		
		return historico;
	}

	@Override
	public String validar(ContasReceber model) {

		return new ContasReceberValidation().validar(model);
		
	}

	@Override
	public ContasReceber inserirLote(ContasReceber model) throws TSApplicationException {
		
		model.setStatus(new Status(0L));

		return new ContasReceberDAO().inserirInterface(model);
		
	}

	@Override
	public void inserirSAP(Empresa empresa) {

		List<ContasReceber> lista = new ArrayList<ContasReceber>();

		for (ContasReceber item : new ContasReceberDAO().pesquisarInterface(new ContasReceber(empresa))) {

			try {
				
				item.setEmpresa(new EmpresaDAO().obter(item.getEmpresa()));

				item.setLinhas(new ContasReceberLinhaDAO().pesquisarInterface(item));
				
				if(Constantes.TIPO_MODALIDADE_BOLETO.equals(item.getTipoModalidade())){
					
					item.setModalidadePagamentoBoleto(new ModalidadePagamentoBoletoDAO().obter(new ContasReceber("interfaceId", item.getInterfaceId())));
					
				}
				
				if(Constantes.TIPO_MODALIDADE_TRANSFERENCIA.equals(item.getTipoModalidade())){
					
					item.setModalidadePagamentoTransferencia(new ModalidadePagamentoTransferenciaDAO().obter(new ContasReceber("interfaceId", item.getInterfaceId())));
					
				}

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new ContasReceberDAO().alterarInterface(item);

				lista.add(item);

			} catch (TSApplicationException e) {

				item.setStatus(new Status(3L));

				item.setDataImportacao(new Timestamp(System.currentTimeMillis()));

				if (!TSUtil.isEmpty(e.getMessage())) {

					item.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

				} else {

					item.setMensagemErro("erro Banco");

				}

				try {

					new HistoricoContasReceberDAO().inserirInterface(this.carregaHistorico(item));

					new ContasReceberDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (ContasReceber item : lista) {

			this.inserir(item);

		}

		
	}

	@Override
	public void alterarStatusInterface() throws TSApplicationException {

		for (ContasReceber item : new ContasReceberDAO().pesquisarPorAtrasadaInterface(new ContasReceber(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new ContasReceberDAO().alterarInterface(item);

		}

		
	}



}
