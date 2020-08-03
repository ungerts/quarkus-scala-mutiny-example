package de.ungerts.scala.rest

import io.smallrye.mutiny
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import de.ungerts.mutiny.scaladsl.Uni
import de.ungerts.mutiny.scala.MutinyConverters._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future


@Path("/hello")
@ApplicationScoped
class HelloResource()(implicit val ec: ExecutionContext) {

    @GET
    @Path("/mutiny")
    @Produces(Array[String](MediaType.TEXT_PLAIN))
    def helloMutiny(): mutiny.Uni[String] = {
        val helloUni = Uni.createFrom().item("Hello")
        val mutinyUni = Uni.createFrom().item("Mutiny")
        val resultUni = for {
            hello <- helloUni
            mutiny <- mutinyUni
        } yield s"$hello $mutiny!"
        resultUni.javaUni
    }

    @GET
    @Path("/scala")
    @Produces(Array[String](MediaType.TEXT_PLAIN))
    def helloScala(): mutiny.Uni[String] = {
        val helloFuture = Future("Hello")
        val scalaFuture = Future("Scala")
        val resultFuture = for {
            hello <- helloFuture
            mutiny <- scalaFuture
        } yield s"$hello $mutiny!"
        resultFuture.toUni
    }

}
