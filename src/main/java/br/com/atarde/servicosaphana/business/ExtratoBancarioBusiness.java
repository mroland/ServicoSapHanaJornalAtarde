package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.dao.ExtratoBancarioDAO;
import br.com.atarde.servicosaphana.dao.HistoricoExtratoBancarioDAO;
import br.com.atarde.servicosaphana.model.HistoricoExtratoBancario;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.validation.ExtratoBancarioValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class ExtratoBancarioBusiness extends MainBusiness<ExtratoBancario> {

	public void inserirSAP(Empresa empresa) {

		List<ExtratoBancario> lista = new ArrayList<ExtratoBancario>();

		for (ExtratoBancario item : new ExtratoBancarioDAO().pesquisarInterface(new ExtratoBancario(empresa))) {

			try {

				item.setEmpresa(new EmpresaDAO().obter(item.getEmpresa()));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				item.setDataAtualizacao(new Timestamp(System.currentTimeMillis()));

				new ExtratoBancarioDAO().alterarInterface(item);

				lista.add(item);

			} catch (TSApplicationException e) {

				item.setStatus(new Status(3L));

				item.setDataImportacao(new Date(System.currentTimeMillis()));

				if (!TSUtil.isEmpty(e.getMessage())) {

					item.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

				} else {

					item.setMensagemErro("erro Banco");

				}

				try {

					new HistoricoExtratoBancarioDAO().inserirInterface(this.carregaHistorico(item));

					new ExtratoBancarioDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (ExtratoBancario item : lista) {

			this.inserir(item);

		}

	}

	private HistoricoExtratoBancario carregaHistorico(ExtratoBancario model) {

		HistoricoExtratoBancario historico = new HistoricoExtratoBancario();

		historico.setDataAtualizacao(model.getDataAtualizacao());

		historico.setDataExportacao(model.getDataExportacao());

		historico.setDataImportacao(model.getDataImportacao());

		historico.setDataVencimento(model.getDataVencimento());

		historico.setEmpresa(model.getEmpresa());

		historico.setMensagemErro(model.getMensagemErro());

		historico.setStatus(model.getStatus());
		
		historico.setCodigoInterno(model.getCodigoInterno());
		
		historico.setConta(model.getConta());
		
		historico.setContaContabil(model.getContaContabil());
		
		historico.setDetalhe(model.getDetalhe());
		
		historico.setNumeroDocumento(model.getNumeroDocumento());
		
		historico.setValorCredito(model.getValorCredito());
		
		historico.setValorDebito(model.getValorDebito());
		
		return historico;
	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (ExtratoBancario item : new ExtratoBancarioDAO().pesquisarPorAtrasadaInterface(new ExtratoBancario(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new ExtratoBancarioDAO().alterarInterface(item);

		}

	}

	@Override
	public ExtratoBancario inserir(ExtratoBancario model) {

		try {

			//new ExtratoBancarioSapDiApiDAO().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			model.setDataImportacao(new Date(System.currentTimeMillis()));

			new HistoricoExtratoBancarioDAO().inserirInterface(this.carregaHistorico(model));

			new ExtratoBancarioDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Date(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoExtratoBancarioDAO().inserirInterface(this.carregaHistorico(model));

				new ExtratoBancarioDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;
	}

	@Override
	public String validar(ExtratoBancario model) {

		return new ExtratoBancarioValidation().validar(model);

	}

	@Override
	public ExtratoBancario inserirLote(ExtratoBancario model) throws TSApplicationException {

		return new ExtratoBancarioDAO().inserirInterface(model);

	}

}
