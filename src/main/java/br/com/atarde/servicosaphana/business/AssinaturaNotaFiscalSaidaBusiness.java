package br.com.atarde.servicosaphana.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosaphana.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.AssinaturaNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosaphana.dao.AssinaturaNotaFiscalSaidaParcelaDAO;
import br.com.atarde.servicosaphana.dao.HistoricoAssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.HistoricoAssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.business.service.AssinaturaNotaFiscalSaidaSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class AssinaturaNotaFiscalSaidaBusiness extends NotaFiscalSaidaBusinessAB{

	public void inserirSAP(Empresa model) {

		List<AssinaturaNotaFiscalSaida> lista = new ArrayList<AssinaturaNotaFiscalSaida>();

		for (AssinaturaNotaFiscalSaida item : new AssinaturaNotaFiscalSaidaDAO().pesquisarInterface(new AssinaturaNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new AssinaturaNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setParcelas(new AssinaturaNotaFiscalSaidaParcelaDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);

				new AssinaturaNotaFiscalSaidaDAO().alterarInterface(item);

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

					new HistoricoAssinaturaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new AssinaturaNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (AssinaturaNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(AssinaturaNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			this.obterSequenciaDefaultParceiroNegocio(model);

			new AssinaturaNotaFiscalSaidaSapBusinessService().inserir((AssinaturaNotaFiscalSaida) model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoAssinaturaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			new AssinaturaNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (Exception e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoAssinaturaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new AssinaturaNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}



	private HistoricoAssinaturaNotaFiscalSaida carregaHistorico(AssinaturaNotaFiscalSaida model) {

		HistoricoAssinaturaNotaFiscalSaida nota = new HistoricoAssinaturaNotaFiscalSaida();
		
		nota.setInterfaceOriginalId(model.getInterfaceId());

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setCliente(model.getCliente());

		nota.setCondicaoPagamento(model.getCondicaoPagamento());

		nota.setCriadoPor(model.getCriadoPor());

		nota.setDataAtualizacao(model.getDataAtualizacao());

		nota.setDataCriacao(model.getDataCriacao());

		nota.setDataDocumento(model.getDataDocumento());

		nota.setDataExportacao(model.getDataExportacao());

		nota.setDataImportacao(new Date());

		nota.setDataLancamento(model.getDataLancamento());

		nota.setDataVencimento(model.getDataVencimento());

		nota.setEmpresa(model.getEmpresa());

		nota.setId(model.getId());

		nota.setIdExterno(model.getIdExterno());

		nota.setInterfaceId(model.getInterfaceId());

		nota.setLinhas(model.getLinhas());

		nota.setMensagemErro(model.getMensagemErro());

		nota.setObservacao(model.getObservacao());

		nota.setOrigem(model.getOrigem());

		nota.setParcela(model.getParcela());

		nota.setParcelas(model.getParcelas());

		nota.setPedidoVenda(model.getPedidoVenda());

		nota.setPercentualDesconto(model.getPercentualDesconto());

		nota.setSequencia(model.getSequencia());

		nota.setSerial(model.getSerial());

		nota.setStatus(model.getStatus());

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setUObservacao(model.getUObservacao());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		nota.setFilial(model.getFilial());
		
		nota.setArquivoRemessa(model.getArquivoRemessa());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (AssinaturaNotaFiscalSaida item : new AssinaturaNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new AssinaturaNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new AssinaturaNotaFiscalSaidaDAO().alterarInterface(item);

		}

	}
}
