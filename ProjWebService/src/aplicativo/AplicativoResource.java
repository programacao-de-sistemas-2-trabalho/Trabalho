package aplicativo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31840175
 */



import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/aplicativos")
@Produces(MediaType.APPLICATION_JSON)
public class AplicativoResource {    
    
    private AplicativoDAO dao;
    
    public AplicativoResource(AplicativoDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Aplicativo create(Aplicativo app) {
        Aplicativo p = dao.criar(app);
        return p;
    }
    
    @GET
    public List<Aplicativo> read() {
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response upadate(long id, Aplicativo app) {
        if (dao.atualizar(id, app)) {
            return Response.ok().build();
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
                }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id, Aplicativo app) {       
        if (dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
