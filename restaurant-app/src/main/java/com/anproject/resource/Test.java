package com.anproject.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class Test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers(){
        return "[{\"id\":1, \"name\":\"fdgsdf\"}, {\"id\":2, \"name\":\"sfdgsdgd\"}]";
    }
}
