package br.com.atarde.servicosaphana.business;

import br.com.atarde.servicosaphana.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.EasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosaphana.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosaphana.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosaphana.sap.dao.OrigemDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosaphana.sap.model.Origem;
import br.com.atarde.servicosaphana.sap.model.Status;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.atarde.servicosaphana.validation.AssinaturaNotaFiscalSaidaValidation;
import br.com.atarde.servicosaphana.validation.ClassificadosContratoNotaFiscalSaidaValidation;
import br.com.atarde.servicosaphana.validation.EasyclassNotaFiscalSaidaValidation;
import br.com.atarde.servicosaphana.validation.VendaAvulsaNotaFiscalSaidaValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class NotaFiscalBusiness extends MainBusiness<NotaFiscalSaidaAB> {

	@Override
	public NotaFiscalSaidaAB inserir(NotaFiscalSaidaAB model) {

		switch (model.getOrigem().getId().intValue()) {

		case 1:// Easyclass

			if (model instanceof EasyclassNotaFiscalSaida) {

				return new EasyclassNotaFiscalSaidaBusiness().inserir((EasyclassNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 2: // VendaAvulsa

			if (model instanceof VendaAvulsaNotaFiscalSaida) {

				return new VendaAvulsaNotaFiscalSaidaBusiness().inserir((VendaAvulsaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 3: // Assinatura

			if (model instanceof AssinaturaNotaFiscalSaida) {

				return new AssinaturaNotaFiscalSaidaBusiness().inserir((AssinaturaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia AssinaturaNotaFiscalSaida.");

			}

			break;
		case 4: // Fotografia

			model.setMensagemErro("Ainda nao implementado Fotografia para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 5: // AtardeOnline

			model.setMensagemErro("Ainda nao implementado AtardeOnline para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 6: // SVG

			model.setMensagemErro("Ainda nao implementado SVG para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 7: // SAP

			model.setMensagemErro("Ainda nao implementado SAP para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 8: // Classificados

			model.setMensagemErro("Ainda nao implementado Classificados para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 9: // Promedica

			model.setMensagemErro("Ainda nao implementado Promedica para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 10: // Confissao Dividas

			model.setMensagemErro("Ainda nao implementado Confissao Dividas para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 11:// Cheque Devolvido

			model.setMensagemErro("Ainda nao implementado Cheque Devolvido para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 12: // Publicidade Online

			model.setMensagemErro("Ainda nao implementado Publicidade Online para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		default:

			model.setMensagemErro("Ainda nao implementado para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		}

		return model;
		
		
	}

	@Override
	public NotaFiscalSaidaAB inserirLote(NotaFiscalSaidaAB model) throws TSApplicationException {

		model.setStatus(new Status(0L));

		switch (model.getOrigem().getId().intValue()) {

		case 1:// Easyclass

			if (model instanceof EasyclassNotaFiscalSaida) {

				return new EasyclassNotaFiscalSaidaDAO().inserirInterface((EasyclassNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia EasyclassNotaFiscalSaida.");

			}

			break;
		case 2: // VendaAvulsa

			if (model instanceof VendaAvulsaNotaFiscalSaida) {

				return new VendaAvulsaNotaFiscalSaidaDAO().inserirInterface((VendaAvulsaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 3: // Assinatura

			if (model instanceof AssinaturaNotaFiscalSaida) {

				return new AssinaturaNotaFiscalSaidaDAO().inserirInterface((AssinaturaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia AssinaturaNotaFiscalSaida.");

			}

			break;

		case 4: // Fotografia

			model.setMensagemErro("Ainda nao implementado Fotografia para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 5: // AtardeOnline

			model.setMensagemErro("Ainda nao implementado AtardeOnline para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 6: // SVG

			model.setMensagemErro("Ainda nao implementado SVG para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 7: // SAP

			model.setMensagemErro("Ainda nao implementado SAP para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 8: // Classificados

			model.setMensagemErro("Ainda nao implementado Classificados para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 9: // Promedica

			model.setMensagemErro("Ainda nao implementado Promedica para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 10: // Confissao Dividas

			model.setMensagemErro("Ainda nao implementado Confissao Dividas para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 11:// Cheque Devolvido

			model.setMensagemErro("Ainda nao implementado Cheque Devolvido para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 12: // Publicidade Online

			model.setMensagemErro("Ainda nao implementado Publicidade Online para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		default:

			model.setMensagemErro("Ainda nao implementado para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		}

		return model;

	}

	@Override
	public String validar(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getOrigem()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getOrigem().getId()))) {
			
			model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

			if (!TSUtil.isEmpty(model.getEmpresa()) && !TSUtil.isEmpty(new OrigemDAO().obter(new Origem(model.getEmpresa(), model.getOrigem().getId())))) {

				switch (model.getOrigem().getId().intValue()) {

				case 1:// Easyclass

					if (model instanceof EasyclassNotaFiscalSaida) {

						retorno.append(new EasyclassNotaFiscalSaidaValidation().validar((EasyclassNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como Easyclass. ");

					}

					break;

				case 2: // VendaAvulsa

					if (model instanceof VendaAvulsaNotaFiscalSaida) {

						retorno.append(new VendaAvulsaNotaFiscalSaidaValidation().validar((VendaAvulsaNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como VendaAvulsaNotaFiscalSaida. ");

					}

					break;

				case 3: // Assinatura

					if (model instanceof AssinaturaNotaFiscalSaida) {

						retorno.append(new AssinaturaNotaFiscalSaidaValidation().validar((AssinaturaNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como AssinaturaNotaFiscalSaida. ");

					}

					break;

				case 4: // Fotografia

					retorno.append("Ainda nao implementado Fotografia para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 5: // AtardeOnline

					retorno.append("Ainda nao implementado AtardeOnline para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 6: // SVG

					retorno.append("Ainda nao implementado SVG para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 7: // SAP

					retorno.append("Ainda nao implementado SAP para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 8: // Classificados

					if (model instanceof ClassificadosContratoNotaFiscalSaida) {

						retorno.append(new ClassificadosContratoNotaFiscalSaidaValidation().validar((ClassificadosContratoNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como ClassificadosContratoNotaFiscalSaida. ");

					}

					break;

				case 9: // Promedica

					retorno.append("Ainda nao implementado Promedica para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 10: // Confissao Dividas

					retorno.append("Ainda nao implementado Confissao Dividas para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 11:// Cheque Devolvido

					retorno.append("Ainda nao implementado Cheque Devolvido para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 12: // Publicidade Online

					retorno.append("Ainda nao implementado Publicidade Online para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				default:

					retorno.append("Ainda nao implementado para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				}

			} else {

				retorno.append("Favor setar objeto NotaFiscalSaidaAB.origem.id válido para a empresaId:" + model.getEmpresa().getId().toString());

			}

		} else {

			retorno.append("Favor setar objeto NotaFiscalSaidaAB.origem.id válido para a empresaId:" + model.getEmpresa().getId().toString());

		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

	@Override
	public void inserirSAP(Empresa empresa) {

	}

	@Override
	public void alterarStatusInterface() throws TSApplicationException {
		// TODO Auto-generated method stub

	}

}
