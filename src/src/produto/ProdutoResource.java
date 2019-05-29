package produto;




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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {    
    
    private ProdutoDAO dao;
    
    public ProdutoResource(ProdutoDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Produto create(Produto pro) {
        Produto p = dao.criar(pro);
        return p;
    }
    
    @GET
    public List<Produto> read() {
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response upadate(long id, Produto p) {
        if (dao.atualizar(id, p)) {
            return Response.ok().build();
        }
        
       throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
     @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id, Produto p) {       
        if (dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
