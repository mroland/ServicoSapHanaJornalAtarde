package br.com.atarde.servicosaphana.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosaphana.sap.model.Contabilidade;

@Path("/helloworld")
public class Abc {

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/listaLote")
	public Contabilidade getContabilidade() {

		Contabilidade conta = new Contabilidade();

		conta.setId(1L);

		conta.setObservacao("Olá Mundo!");

		return conta;
	}
	
	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/listaLote2")
	public Response getContabilidade2() {

		Contabilidade conta = new Contabilidade();

		conta.setId(1L);

		conta.setObservacao("Olá Mundo!");

		return Response.ok(conta).build();
	}
	
	@GET
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/listaLoteTxt")
	public Response getContabilidadeTxt() {

		String conta = "Olá Mundo!";

		return Response.ok(conta).build();
		
	}	

	
	
	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/listaLote3")
	public Response getContabilidade3() {

		String conta = "Olá Mundo!";

		return Response.ok(conta).build();
		
	}	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
	@Path("/inserirLote")
	public Response setContabilidade(Contabilidade c) {

		Contabilidade conta = new Contabilidade();

		conta.setId(7L);
		
		conta.setObservacao("Olá Mundo!");		

		//return Response.status(201).entity(conta).build();
		
		return Response.ok(conta).build();

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
	@Path("/lista")
	public Response setLista(Contabilidade c) {

		List<Contabilidade> lista = new ArrayList<Contabilidade>();
		
		Contabilidade conta = new Contabilidade();
		
		conta.setId(7L);
		
		conta.setObservacao("Olá Mundo!");
		
		lista.add(conta);
		
		GenericEntity<List<Contabilidade>> entity = new GenericEntity<List<Contabilidade>>(lista) {};

		Response response = Response.ok(entity).build();		

		return response;

	}	
	
/*	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
	@Path("/inserirDocumento")
	public Response setDocumento(DocumentoAB c) {
		
		if(c instanceof Teste){
			
			Teste t = (Teste) c;
			
			t.setId(99L);
			

		}
		
		return Response.ok(c).build();

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
	@Path("/inserirDocumentoConcreto")
	public Response setDocumento1(Teste c) {
		
		if(c instanceof Teste){
			
			Teste t = (Teste) c;
			
			t.setId(99L);
			
		}
		
		return Response.ok(c).build();

	}	
	
	*/

}
