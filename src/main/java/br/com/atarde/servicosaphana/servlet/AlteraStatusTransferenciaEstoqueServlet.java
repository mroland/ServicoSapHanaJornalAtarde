package br.com.atarde.servicosaphana.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atarde.servicosaphana.business.TransferenciaEstoqueBusiness;
import br.com.topsys.exception.TSApplicationException;

/**
 * Servlet implementation class AlteraStatusVendaAvulsaNotaFiscalSaidaServlet
 */
@WebServlet("/AlteraStatusTransferenciaEstoqueServlet")
public class AlteraStatusTransferenciaEstoqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraStatusTransferenciaEstoqueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            new TransferenciaEstoqueBusiness().alterarStatusInterface();
        } catch (TSApplicationException ex) {
            Logger.getLogger(AlteraStatusTransferenciaEstoqueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
