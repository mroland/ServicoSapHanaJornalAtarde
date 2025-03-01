package br.com.atarde.servicosaphana.validation;

import br.com.atarde.servicosaphana.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosaphana.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosaphana.sap.model.CFOP;
import br.com.atarde.servicosaphana.sap.model.CST;
import br.com.atarde.servicosaphana.sap.model.CodigoImposto;
import br.com.atarde.servicosaphana.sap.model.Filial;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.PedidoVenda;
import br.com.atarde.servicosaphana.sap.model.PedidoVendaLinha;
import br.com.atarde.servicosaphana.sap.model.Utilizacao;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class AssinaturaNotaFiscalSaidaValidation extends NotaFiscalSaidaValidation {

	public String validar(AssinaturaNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validar(model));

		if (TSUtil.isEmpty(retorno.toString())) {

			retorno.append(this.validaNFF(model));

		}

		return retorno.toString();

	}

	public String validaNFF(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(retorno.toString())) {

			AssinaturaNotaFiscalSaida nota = (AssinaturaNotaFiscalSaida) model;

			if (!TSUtil.isEmpty(new AssinaturaNotaFiscalSaidaDAO().obterIdExternoInterface(nota))) {

				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");

				return retorno.toString();

			}

			if (TSUtil.isEmpty(Utilitarios.tratarString(nota.getCliente().getId()))) {

				if ((nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0) && (TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) || (!TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) && nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual().length() > 15))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

			}

			if (TSUtil.isEmpty(nota.getObservacao()) || nota.getObservacao().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setObservacao(nota.getObservacao().toUpperCase());

			}

			if (TSUtil.isEmpty(nota.getUObservacao()) || nota.getUObservacao().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUObservacao(nota.getUObservacao().toUpperCase());
			}

			if (!TSUtil.isEmpty(nota.getUTermo()) && nota.getUTermo().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_TERMO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUEnderecoEntrega()) || (!TSUtil.isEmpty(nota.getUEnderecoEntrega()) && nota.getUEnderecoEntrega().length() > 254)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUEnderecoEntrega(nota.getUEnderecoEntrega().toUpperCase());
			}

			if (!TSUtil.isEmpty(nota.getLinhas()) || (!TSUtil.isEmpty(nota.getLinhas()) && nota.getLinhas().size() != 0)) {

				for (AssinaturaNotaFiscalSaidaLinha linha : nota.getLinhas()) {

					linha.setEmpresa(model.getEmpresa());

					// colocar fixo para que nao impacte os sistemas legados de implementar
					linha.setCodigoImposto(new CodigoImposto());

					// linha.getCodigoImposto().setId("5101-006");

					linha.setUtilizacao(new Utilizacao());

					linha.getUtilizacao().setId(9L);

					linha.setCfop(new CFOP());

					linha.setCstIPI(new CST());

					linha.setCstPIS(new CST());

					linha.setCstCOFINS(new CST());

					linha.setCstICMS(new CST());

					retorno.append(this.validaLinhaNFF(linha, model.getFilial()));

				}

			} else {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA + "\n");

			}

		}

		return retorno.toString();

	}

	protected String validaLinhaNFF(AssinaturaNotaFiscalSaidaLinha model, Filial filial) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validaLinhaNFF(model, filial));

		if (!TSUtil.isEmpty(model.getPedidoVendaLinha())) {

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getNumero())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA_LINHA_NUMERO);

			}

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getPedidoVenda()) || (TSUtil.isEmpty(Utilitarios.tratarLong(model.getPedidoVendaLinha().getPedidoVenda().getId())))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA);

			}

		} else {

			model.setPedidoVendaLinha(new PedidoVendaLinha());

			model.getPedidoVendaLinha().setPedidoVenda(new PedidoVenda());

		}

		if (!TSUtil.isEmpty(model.getContaContabil()) && (!TSUtil.isEmpty(Utilitarios.tratarString(model.getContaContabil().getId())))) {

			model.getContaContabil().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new ContaContabilDAO().obter(model.getContaContabil()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		return retorno.toString();

	}

}
