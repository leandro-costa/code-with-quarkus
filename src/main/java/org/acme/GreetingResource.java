package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/hello")
public class GreetingResource {

    @GET()
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Alow from RESTEasy Reactive";
    }
}
