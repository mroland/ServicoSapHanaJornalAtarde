package br.com.atarde.servicosaphana.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosaphana.business.ExtratoBancarioBusiness;
import br.com.atarde.servicosaphana.sap.model.ExtratoBancario;
import br.com.topsys.exception.TSApplicationException;

@Path(value="/extratoBancarioWS")
public class ExtratoBancarioWS {

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path(value="/inserirLote")
	public Response inserirLote(ExtratoBancario model){
		
		try {
			
			ExtratoBancario conta = new ExtratoBancarioBusiness().insertBatchEvent(model);
			
			return Response.ok(conta).build();
			
		} catch (TSApplicationException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path(value="/inserir")
	public Response inserir(ExtratoBancario model){
		
		try {
			
			ExtratoBancario conta = new ExtratoBancarioBusiness().insertEvent(model);
			
			return Response.ok(conta).build();
					
		} catch (TSApplicationException e) {

			e.printStackTrace();
			
		}
		
		return null;
		
	}	
	
}
