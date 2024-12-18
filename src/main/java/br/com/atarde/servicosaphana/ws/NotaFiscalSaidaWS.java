package br.com.atarde.servicosaphana.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosaphana.business.NotaFiscalBusiness;
import br.com.atarde.servicosaphana.sap.model.NotaFiscalSaidaAB;
import br.com.topsys.exception.TSApplicationException;

@Path(value = "/notaFiscalSaidaWS")
public class NotaFiscalSaidaWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/inserirLote")
	public Response inserirLote(NotaFiscalSaidaAB model) {

		try {

			NotaFiscalSaidaAB conta = new NotaFiscalBusiness().insertBatchEvent(model);

			return Response.ok(conta).build();

		} catch (TSApplicationException e) {

			e.printStackTrace();

			model.setMensagemErro(e.getMessage());

		}

		return Response.ok(model).build();

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/inserir")
	public Response inserir(NotaFiscalSaidaAB model) {

		try {

			NotaFiscalSaidaAB conta = new NotaFiscalBusiness().insertEvent(model);

			return Response.ok(conta).build();

		} catch (TSApplicationException e) {

			e.printStackTrace();

			model.setMensagemErro(e.getMessage());

		}

		return Response.ok(model).build();

	}

}
