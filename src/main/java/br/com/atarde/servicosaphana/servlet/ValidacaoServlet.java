package br.com.atarde.servicosaphana.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atarde.servicosaphana.business.ContabilidadeBusiness;
import br.com.atarde.servicosaphana.dao.EmpresaDAO;
import br.com.atarde.servicosaphana.sap.model.CentroCusto;
import br.com.atarde.servicosaphana.sap.model.Classificacao;
import br.com.atarde.servicosaphana.sap.model.ContaContabil;
import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.atarde.servicosaphana.sap.model.ContabilidadeLinha;
import br.com.atarde.servicosaphana.sap.model.Empresa;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.atarde.servicosaphana.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocio;
import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioEndereco;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

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
		
		//this.validarContabilidade();
		
		this.validarExtratoBancario();
		
		//this.validarCentroCusto();
		
	}

	private void validarCentroCusto() {

		CentroCusto c = new CentroCusto();
		
		c.setEmpresa(new EmpresaDAO().obter(new Empresa(1L)));
		
		//try {
			//new CentroCustoDiApiDAO().inserir(c);
		//} catch (TSApplicationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	}

	private void validarExtratoBancario() {

		ExtratoBancario extrato = new ExtratoBancario();
		
		extrato.setEmpresa(new EmpresaDAO().obter(new Empresa(1L)));
		
		//try {
			//new ExtratoBancarioSapDiApiDAO().inserir(extrato);
			
			//new ExtratoBancarioSapDiApiDAO().inserir(extrato);
		//} catch (TSApplicationException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
	}

	private void validarContabilidade() {

		Contabilidade model = new Contabilidade();
		
		ContabilidadeLinha linha = new ContabilidadeLinha();
		
		ContabilidadeLinha linha2 = new ContabilidadeLinha();
		
		model.setDataVencimento(new Date());
		
		model.setDataDocumento(new Date());
		
		model.setDataLancamento(new Date());
		
		model.setEmpresa(new Empresa(1L));
		
		linha.setValorCredito(BigDecimal.ZERO);
		
		linha.setValorDebito(BigDecimal.TEN);
			
		linha.setContaContabil(new ContaContabil());
		
		linha.getContaContabil().setId("1.1.1.1.2");
		
		linha.setNumero(1);

		linha2.setValorCredito(BigDecimal.TEN);
		
		linha2.setValorDebito(BigDecimal.ZERO);
		
		linha2.setParceiroNegocio(new ParceiroNegocio());
		
		//linha2.getParceiroNegocio().setId("C00009");
		
		linha2.getParceiroNegocio().setNome("Mroland");
		
		linha2.getParceiroNegocio().setTipo("C");
		
		linha2.getParceiroNegocio().setIdentificadorFiscal(new IdentificadorFiscal());
		
		linha2.getParceiroNegocio().getIdentificadorFiscal().setTipoIdentificador(0);
		
		linha2.getParceiroNegocio().getIdentificadorFiscal().setIdentificador("15234222000130");
		
		linha2.getParceiroNegocio().getIdentificadorFiscal().setInscricaoEstadual("12345678901234567890");
		
		linha2.getParceiroNegocio().setEndereco(new ParceiroNegocioEndereco());
		
		linha2.getParceiroNegocio().setClassificacao(new Classificacao());
		
		linha2.getParceiroNegocio().getClassificacao().setId(111L);
		
		linha2.setNumero(2);
		
		model.setLinhas(new ArrayList<ContabilidadeLinha>());
				
		model.getLinhas().add(linha);
		
		model.getLinhas().add(linha2);
		
		try {
			new ContabilidadeBusiness().insertBatchEvent(model);
		} catch (TSApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!TSUtil.isEmpty(model.getMensagemErro())){
			
			System.out.println(model.getMensagemErro());
			
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
