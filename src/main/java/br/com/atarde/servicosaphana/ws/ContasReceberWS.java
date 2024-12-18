package br.com.atarde.servicosaphana.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosaphana.business.ContasReceberBusiness;
import br.com.atarde.servicosaphana.sap.model.ContasReceber;
import br.com.topsys.exception.TSApplicationException;

@Path(value="/contasReceberWS")
public class ContasReceberWS {
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path(value="/inserirLote")
	public Response inserirLote(ContasReceber model){
		
		try {
			
			return Response.status(201).entity(new ContasReceberBusiness().insertBatchEvent(model)).build();
			
		} catch (TSApplicationException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path(value="/inserir")
	public Response inserir(ContasReceber model){
		
		try {
			
			return Response.status(201).entity(new ContasReceberBusiness().insertEvent(model)).build();
					
		} catch (TSApplicationException e) {

			e.printStackTrace();
			
		}
		
		return null;
		
	}	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/inserirLote2")
	public Response setContasReceber(ContasReceber c) {

		ContasReceber conta = new ContasReceber();

		conta.setId(7L);
		
		conta.setMensagemErro("TESTE");

		return Response.status(201).entity(conta).build();

	}	

}
