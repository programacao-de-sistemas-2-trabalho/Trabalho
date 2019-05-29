package time;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("/times")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {    
    
    private TimeDAO dao;
    
    public TimeResource(TimeDAO dao) {
        this.dao = dao;
    }
    
    @POST
    public Time create(Time t) {
        Time p = dao.criar(t);
        return p;
    }
    
    @GET
    public List<Time> read() {
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response upadate(long id, Time t) {
        if (dao.atualizar(id, t)) {
            return Response.ok().build();
        }        
       throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id, Time t) {       
        if (dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}