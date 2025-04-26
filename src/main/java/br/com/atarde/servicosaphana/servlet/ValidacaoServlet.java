package br.com.atarde.servicosaphana.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;

/**
 * Servlet implementation class ValidacaoServlet
 */
@WebServlet("/ValidacaoServlet")
public class ValidacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidacaoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// this.validarContabilidade();

		this.validarExtratoBancario();

		// this.validarCentroCusto();

	}

	private void validarExtratoBancario() {

		ExtratoBancario extrato = new ExtratoBancario();

		extrato.setEmpresa(new EmpresaDAO().obter(new Empresa(1L)));

		// try {
		// new ExtratoBancarioSapDiApiDAO().inserir(extrato);

		// new ExtratoBancarioSapDiApiDAO().inserir(extrato);
		// } catch (TSApplicationException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
