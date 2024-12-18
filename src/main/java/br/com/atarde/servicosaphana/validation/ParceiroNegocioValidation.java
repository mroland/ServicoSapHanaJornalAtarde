package br.com.atarde.servicosaphana.validation;

import br.com.atarde.servicosaphana.sap.dao.ClassificacaoDAO;
import br.com.atarde.servicosaphana.sap.dao.EstadoDAO;
import br.com.atarde.servicosaphana.sap.dao.MunicipioDAO;
import br.com.atarde.servicosaphana.sap.dao.PaisDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosaphana.sap.dao.ParceiroNegocioEnderecoDAO;
import br.com.atarde.servicosaphana.sap.model.Classificacao;
import br.com.atarde.servicosaphana.sap.model.EnderecoAB;
import br.com.atarde.servicosaphana.sap.model.Estado;
import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosaphana.sap.model.Municipio;
import br.com.atarde.servicosaphana.sap.model.Pais;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;
import br.com.atarde.servicosaphana.util.Constantes;
import br.com.atarde.servicosaphana.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class ParceiroNegocioValidation {

	public String validar(ParceiroNegocio model) {

		StringBuilder retorno = new StringBuilder();

		if (!TSUtil.isEmpty(model)) {

			model.setFlagEndereco(Boolean.TRUE);

			if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getId()))) {

				ParceiroNegocio parceiroBanco = new ParceiroNegocioDAO().obter(model);

				if (TSUtil.isEmpty(parceiroBanco)) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO + "\n");

				} else {

					if (!parceiroBanco.getFlagAtivo()) {

						retorno.append("O parceiro de negócio " + parceiroBanco.getId() + " está inativo " + "\n");

					}

					model.setClassificacao(new Classificacao());

					model.setIdentificadorFiscal(new IdentificadorFiscal());

					model.setEndereco(new ParceiroNegocioEndereco());

					model.getEndereco().setEstado(new Estado());

					model.getEndereco().setPais(new Pais());

					model.getEndereco().setMunicipio(new Municipio());

					model.setFlagEndereco(Boolean.FALSE);

					if (!TSUtil.isEmpty(model.getEnderecoEntregaDefault())) {

						ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

						endereco.setEmpresa(model.getEmpresa());

						endereco.setTipoEndereco("S");

						endereco.setParceiroNegocio(new ParceiroNegocio());

						endereco.getParceiroNegocio().setId(model.getId());

						endereco.setId(model.getEnderecoEntregaDefault());

						if (TSUtil.isEmpty(new ParceiroNegocioEnderecoDAO().obter(endereco))) {

							retorno.append(Constantes.PARCEIRO_NEGOCIO_ENDERECO_ID + "\n");

						}

					}

					if (!TSUtil.isEmpty(model.getEnderecoCobrancaDefault())) {

						ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

						endereco.setEmpresa(model.getEmpresa());

						endereco.setTipoEndereco("B");

						endereco.setParceiroNegocio(new ParceiroNegocio());

						endereco.getParceiroNegocio().setId(model.getId());

						endereco.setId(model.getEnderecoCobrancaDefault());

						if (TSUtil.isEmpty(new ParceiroNegocioEnderecoDAO().obter(endereco))) {

							retorno.append(Constantes.PARCEIRO_NEGOCIO_ENDERECO_ID + "\n");

						}

					}

				}

			} else {

				retorno.append(this.validaPessoa(model));

				if (TSUtil.isEmpty(model.getClassificacao())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_CLASSIFICACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

				} else {

					model.getClassificacao().setEmpresa(model.getEmpresa());

					ParceiroNegocio parceiro = new ParceiroNegocio();

					parceiro.setId(model.getId());

					parceiro.setEmpresa(model.getEmpresa());

					parceiro.setTipo(model.getTipo());

					model.getClassificacao().setParceiroNegocio(parceiro);

					if (TSUtil.isEmpty(new ClassificacaoDAO().obter(model.getClassificacao()))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_CLASSIFICACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

				}

				if (TSUtil.isEmpty(model.getEndereco())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO + "\n");

				} else {

					model.getEndereco().setEmpresa(model.getEmpresa());

					retorno.append(this.validaEndereco(model.getEndereco()));

				}

			}

		} else {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO + "\n");

		}

		return retorno.toString();
	}

	private String validaEndereco(EnderecoAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getTipoLogradouro()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getTipoLogradouro())) && model.getTipoLogradouro().length() > 100)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_TIPO_LOGRADOURO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setTipoLogradouro(model.getTipoLogradouro().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getLogradouro()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getLogradouro())) && model.getLogradouro().length() > 100)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_LOGRADOURO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setLogradouro(model.getLogradouro().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getNumero()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getNumero())) && model.getNumero().length() > 20)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_NUMERO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setNumero(model.getNumero().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getBairro()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getBairro())) && model.getBairro().length() > 100)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_BAIRRO + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setBairro(model.getBairro().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getCidade()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getCidade())) && model.getCidade().length() > 100)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_CIDADE + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setCidade(model.getCidade().toUpperCase());

		}

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getComplemento()))) {

			model.setComplemento(model.getComplemento().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getPais()) || TSUtil.isEmpty(Utilitarios.tratarString(model.getPais().getId())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getPais().getId())) && model.getPais().getId().length() > 3) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getPais().getId())) && TSUtil.isEmpty(new PaisDAO().obter(new Pais(model.getPais().getId(), model.getEmpresa()))))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_PAIS + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			if (TSUtil.isEmpty(model.getEstado()) || TSUtil.isEmpty(Utilitarios.tratarString(model.getEstado().getId())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getEstado().getId())) && model.getEstado().getId().length() > 3) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getEstado().getId())) && TSUtil.isEmpty(new EstadoDAO().obter(new Estado(model.getEstado().getId(), model.getEmpresa()))))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_ESTADO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (!TSUtil.isEmpty(model.getEstado()) && (!TSUtil.isEmpty(model.getCidade())) && (!TSUtil.isEmpty(model.getPais()))) {

				Municipio municipio = new Municipio();

				municipio.setEnderecoAB(model);

				municipio.setEmpresa(model.getEmpresa());

				municipio = new MunicipioDAO().obter(municipio);

				if (TSUtil.isEmpty(municipio)) {

					retorno.append(Constantes.MUNICIPIO_INEXISTENTE + "\n");

				} else {

					model.setMunicipio(municipio);

				}

			}

		}

		if (TSUtil.isEmpty(model.getCep()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getCep())) && model.getCep().length() > 20)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ENDERECO_CEP + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setCep(model.getCep().replace(".", "").replace("/", "").replace("-", "").replace("|", "").replace("\\", ""));

			if (model.getCep().length() == 8) {

				model.setCep(model.getCep().substring(0, 5).concat("-").concat(model.getCep().substring(5, 8)));

			}

		}

		return retorno.toString();
	}

	private String validaPessoa(ParceiroNegocio model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(Utilitarios.tratarString(model.getTipo()))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_TIPO_PARCEIRO_NEGOCIO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || TSUtil.isEmpty(model.getIdentificadorFiscal().getTipoIdentificador())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_TIPO_IDENTIFICADOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || (TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getIdentificador()))) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getIdentificador())) && model.getIdentificadorFiscal().getIdentificador().length() > 20)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_IDENTIFICADOR + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getIdentificadorFiscal().setIdentificador(model.getIdentificadorFiscal().getIdentificador().replace(".", "").replace("/", "").replace("-", ""));

			if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getTipo()))) {

				ParceiroNegocio parceiroBanco = new ParceiroNegocio();

				parceiroBanco.setId(model.getTipo() + model.getIdentificadorFiscal().getIdentificador());

				parceiroBanco.setEmpresa(model.getEmpresa());

				parceiroBanco = new ParceiroNegocioDAO().obter(parceiroBanco);

				if (!TSUtil.isEmpty(parceiroBanco) && !parceiroBanco.getFlagAtivo()) {

					retorno.append("O parceiro de negócio " + parceiroBanco.getId() + " está inativo " + "\n");

				}

			}

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getInscricaoEstadual())) && model.getIdentificadorFiscal().getInscricaoEstadual().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL + "\n");

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria())) && model.getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL_SUBSTITUTO_TRIBUTARIA + "\n");

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getInscricaoMunicipal())) && model.getIdentificadorFiscal().getInscricaoMunicipal().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_INSCRICAO_MUNICIPAL + "\n");

		}

		if ((TSUtil.isEmpty(model.getIdentificadorFiscal())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getIdentificadorFiscal().getInscricaoINSS())) && model.getIdentificadorFiscal().getInscricaoINSS().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_INSCRICAO_INSS + "\n");

		}

		if (TSUtil.isEmpty(model.getNome()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getNome())) && model.getNome().length() > 100)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOME + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setNome(model.getNome().toUpperCase());

		}

		if (TSUtil.isEmpty(model.getNomeFantasia()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getNomeFantasia())) && model.getNomeFantasia().length() > 100)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_NOME_FANTASIA + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.setNomeFantasia(model.getNomeFantasia().toUpperCase());

		}

		if ((!TSUtil.isEmpty(Utilitarios.tratarString(model.getTelefoneResidencial())) && model.getTelefoneResidencial().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_TELEFONE_RESIDENCIAL + "\n");

		}

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getTelefoneCelular()))) {

			if (model.getTelefoneCelular().length() > 15) {

				retorno.append(Constantes.PARCEIRO_NEGOCIO_TELEFONE_CELULAR + "\n");

			} else {

				model.setTelefoneCelular(model.getTelefoneCelular().replace(".", "").replace("/", "").replace("-", "").replace("|", "").replace("\\", "").replace("(", "").replace(")", "").replaceAll(" ", ""));

				if (model.getTelefoneCelular().length() == 11) {

					model.setTelefoneCelular("(".concat(model.getTelefoneCelular().substring(0, 2)).concat(")").concat(model.getTelefoneCelular().substring(2, 7)).concat("-").concat(model.getTelefoneCelular().substring(7, 11)));

				}

			}

		}

		if (TSUtil.isEmpty(Utilitarios.tratarString(model.getTelefoneCelular())) && TSUtil.isEmpty(Utilitarios.tratarString(model.getTelefoneResidencial()))) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_TELEFONE_OBRIGATORIO + "\n");

		}

		if ((!TSUtil.isEmpty(Utilitarios.tratarString(model.getFax())) && model.getFax().length() > 15)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_TELEFONE_FAX + "\n");

		}

		if (TSUtil.isEmpty(Utilitarios.tratarString(model.getEmail())) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getEmail())) && model.getEmail().length() > 50)) {

			retorno.append(Constantes.PARCEIRO_NEGOCIO_TELEFONE_EMAIL + "\n");

		}

		if ((!TSUtil.isEmpty(Utilitarios.tratarString(model.getObservacao())))) {

			if (model.getObservacao().length() > 100) {

				retorno.append(Constantes.PARCEIRO_NEGOCIO_OBSERVACAO + "\n");

			} else {

				model.setObservacao(model.getObservacao().toUpperCase());

			}

		}

		return retorno.toString();

	}

}
