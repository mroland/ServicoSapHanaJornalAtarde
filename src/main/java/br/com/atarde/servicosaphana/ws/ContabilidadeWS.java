package br.com.atarde.servicosaphana.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosaphana.business.ContabilidadeBusiness;
import br.com.atarde.servicosaphana.sap.model.Contabilidade;
import br.com.topsys.exception.TSApplicationException;

@Path(value="/contabilidadeWS")
public class ContabilidadeWS {
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path(value="/inserirLote")
	public Response inserirLote(Contabilidade model){
		
		try {
			
			Contabilidade conta = new ContabilidadeBusiness().insertBatchEvent(model);
			
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
	public Response inserir(Contabilidade model){
		
		try {
			
			Contabilidade conta = new ContabilidadeBusiness().insertEvent(model);
			
			return Response.ok(conta).build();
					
		} catch (TSApplicationException e) {

			e.printStackTrace();
			
		}
		
		return null;
		
	}	

}
