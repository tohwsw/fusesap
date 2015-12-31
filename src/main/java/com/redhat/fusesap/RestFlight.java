package com.redhat.fusesap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/")
public class RestFlight {
	
	@POST
    @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
    public String doPost(String customerName, String maxRows){
        return null;
    }
}
