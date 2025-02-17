package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.ContabilidadeDAO;
import br.com.atarde.servicosaphana.dao.ContabilidadeLinhaDAO;
import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoContabilidadeDAO;
import br.com.atarde.servicosaphana.model.HistoricoContabilidade;
import br.com.atarde.servicosaphana.sap.business.service.ParceiroNegocioSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.validation.ContabilidadeValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class ContabilidadeBusiness extends MainBusiness<Contabilidade> {

	public void inserirSAP(Empresa empresa) {

		List<Contabilidade> lista = new ArrayList<Contabilidade>();

		for (Contabilidade item : new ContabilidadeDAO().pesquisarInterface(new Contabilidade(empresa))) {

			try {

				item.setLinhas(new ContabilidadeLinhaDAO().pesquisarInterface(item));

				item.setEmpresa(new EmpresaDAO().obter(item.getEmpresa()));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				item.setDataAtualizacao(new Timestamp(System.currentTimeMillis()));

				new ContabilidadeDAO().alterarInterface(item);

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

					new HistoricoContabilidadeDAO().inserirInterface(this.carregaHistorico(item));

					new ContabilidadeDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (Contabilidade item : lista) {

			this.inserir(item);

		}

	}

	private HistoricoContabilidade carregaHistorico(Contabilidade model) {

		HistoricoContabilidade historico = new HistoricoContabilidade();

		historico.setDataAtualizacao(model.getDataAtualizacao());

		historico.setDataDocumento(model.getDataDocumento());

		historico.setDataExportacao(model.getDataExportacao());

		historico.setDataImportacao(model.getDataImportacao());

		historico.setDataLancamento(model.getDataLancamento());

		historico.setDataVencimento(model.getDataVencimento());

		historico.setEmpresa(model.getEmpresa());

		historico.setLinhas(model.getLinhas());

		historico.setMensagemErro(model.getMensagemErro());

		historico.setObservacao(model.getObservacao());

		historico.setStatus(model.getStatus());

		historico.setId(model.getId());

		return historico;
	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (Contabilidade item : new ContabilidadeDAO().pesquisarPorAtrasadaInterface(new Contabilidade(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new ContabilidadeDAO().alterarInterface(item);

		}

	}

	@Override
	public Contabilidade inserir(Contabilidade model) {

		try {

			for (ContabilidadeLinha item : model.getLinhas()) {

				if (!TSUtil.isEmpty(item.getParceiroNegocio()) && TSUtil.isEmpty(item.getContaContabil())) {

					new ParceiroNegocioSapBusinessService().inserirComEndereco(item.getParceiroNegocio());

				}

			}

			//new ContabilidadeSapDiApiDAO().inserir(model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			model.setDataImportacao(new Date(System.currentTimeMillis()));

			new HistoricoContabilidadeDAO().inserirInterface(this.carregaHistorico(model));

			new ContabilidadeDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Date(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoContabilidadeDAO().inserirInterface(this.carregaHistorico(model));

				new ContabilidadeDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;
	}

	@Override
	public String validar(Contabilidade model) {

		return new ContabilidadeValidation().validar(model);

	}

	@Override
	public Contabilidade inserirLote(Contabilidade model) throws TSApplicationException {

		return new ContabilidadeDAO().inserirInterface(model);

	}

}
