package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.RadioNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.dao.ItemDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.dao.SequenciaDAO;
import br.com.atarde.servicosaphana.sap.model.Item;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.Sequencia;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public abstract class NotaFiscalSaidaBusinessAB {

	public void obterSequenciaDefaultParceiroNegocio(AssinaturaNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
	
	public void obterSequenciaDefaultParceiroNegocio(VendaAvulsaNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
	
	public void obterSequenciaDefaultParceiroNegocio(ClassificadosContratoNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
	
	public void obterSequenciaDefaultParceiroNegocio(ClassificadosExecucaoNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
	
	public void obterSequenciaDefaultParceiroNegocio(EasyclassNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
	
	public void obterSequenciaDefaultParceiroNegocio(RadioNotaFiscalSaida model) throws Exception {

		ParceiroNegocio parceiro = new ParceiroNegocioDAO().obter(model.getCliente());

		model.getLinhas().get(0).getItem().setEmpresa(model.getEmpresa());

		Item item = new ItemDAO().obter(model.getLinhas().get(0).getItem());

		Sequencia sequencia = null;

		if (parceiro.getUTipoDocumento().equals(Constantes.TIPO_DOCUMENTO_SEQUENCIA_NOTA)) {

			if (item.getFlagControleEstoque()) {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.TRUE);

			} else {

				sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), Boolean.FALSE);

			}

		} else {

			sequencia = new SequenciaDAO().obterInterface(parceiro.getuTipoDocumento(), model.getFilial(), null);

		}

		if (TSUtil.isEmpty(sequencia)) {

			throw new Exception("Sequencia não mapeada na interface para filial e parceiro.tipoDocumento");

		}

		model.getSequencia().setId(sequencia.getIdExterno());

	}
}
