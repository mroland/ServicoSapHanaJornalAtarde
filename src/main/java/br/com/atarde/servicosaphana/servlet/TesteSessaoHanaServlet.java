package br.com.atarde.servicosaphana.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.business.service.ParceiroNegocioSapBusinessService;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.Estado;
import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosaphana.sap.model.Municipio;
import br.com.atarde.servicosaphana.sap.model.Pais;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;

/**
 * Servlet implementation class TesteSessaoHanaServlet
 */
@WebServlet("/TesteSessaoHanaServlet")
public class TesteSessaoHanaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TesteSessaoHanaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// this.testeConexao();

		this.inserirParceiroNegocio();

	}

	private void inserirParceiroNegocio() {

		ParceiroNegocio parceiro = new ParceiroNegocio();
		parceiro.setTipo("C");
		parceiro.setNome("Marcus Roland");
		parceiro.setIdentificadorFiscal(new IdentificadorFiscal());
		parceiro.getIdentificadorFiscal().setTipoIdentificador(1);
		parceiro.getIdentificadorFiscal().setIdentificador("94844445553");
		parceiro.setEmpresa(new EmpresaDAO().obter(new Empresa(4L)));

		parceiro.setId("C94844445553");

		parceiro.setEndereco(new ParceiroNegocioEndereco());

		parceiro.getEndereco().setBairro("Imbui");
		parceiro.getEndereco().setCep("41720100");
		parceiro.getEndereco().setCidade("Salvador");
		parceiro.getEndereco().setComplemento("Ao lado da rotatória");
		parceiro.getEndereco().setEstado(new Estado());
		parceiro.getEndereco().getEstado().setId("BA");
		parceiro.getEndereco().setPais(new Pais());
		parceiro.getEndereco().getPais().setId("BR");
		parceiro.getEndereco().setMunicipio(new Municipio());
		parceiro.getEndereco().setNumero("590");
		parceiro.getEndereco().getMunicipio().setId(537L);
		parceiro.getEndereco().setLogradouro("Rua das patativas");
		parceiro.getEndereco().setTipoLogradouro("Rua");

		// testar criando endereco unico
		parceiro.getEndereco().setTipoEndereco("S");
		parceiro.getEndereco().setId("ENDERECO DE ENTREGA");

		try {
			new ParceiroNegocioSapBusinessService().inserirComEndereco(parceiro);

			// new ParceiroNegocioSapDiApiDAO().inserirSemEndereco(parceiro);

			// new ParceiroNegocioSapDiApiDAO().inserirEnderecoCliente(parceiro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
