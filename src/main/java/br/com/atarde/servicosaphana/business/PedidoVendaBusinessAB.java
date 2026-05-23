package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.model.ClassificadosContratoPedidoVenda;
import br.com.atarde.servicosaphana.model.ClassificadosExecucaoPedidoVenda;
import br.com.atarde.servicosaphana.model.EasyclassPedidoVenda;
import br.com.atarde.servicosaphana.model.RadioPedidoVenda;
import br.com.atarde.servicosaphana.sap.dao.ItemDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.dao.SequenciaDAO;
import br.com.atarde.servicosaphana.sap.model.Item;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.Sequencia;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.topsys.util.TSUtil;

public abstract class PedidoVendaBusinessAB {

	/*
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

*/

	public void obterSequenciaDefaultParceiroNegocio(ClassificadosExecucaoPedidoVenda model) throws Exception {

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
	
	public void obterSequenciaDefaultParceiroNegocio(ClassificadosContratoPedidoVenda model) throws Exception {

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
	
	public void obterSequenciaDefaultParceiroNegocio(EasyclassPedidoVenda model) throws Exception {

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

	public void obterSequenciaDefaultParceiroNegocio(RadioPedidoVenda model) throws Exception {

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
/*
	public void obterSequenciaDefaultParceiroNegocio(DevolucaoNotaFiscalSaida model) throws Exception {

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
	*/
}
