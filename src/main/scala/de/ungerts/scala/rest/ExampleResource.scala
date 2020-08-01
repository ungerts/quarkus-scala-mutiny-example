package de.ungerts.scala.rest

import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.core.MediaType

@Path("/hello")
class ExampleResource {

    @GET
    @Produces(Array[String](MediaType.TEXT_PLAIN))
    def hello() = "hello"
}