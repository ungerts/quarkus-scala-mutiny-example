package de.ungerts.scala.rest

import de.ungerts.scala.dao.PersonDao
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}
import de.ungerts.mutiny.scala.MutinyConverters._

import scala.concurrent.ExecutionContext
import io.smallrye.mutiny

@Path("/persons")
@ApplicationScoped
class PersonResource(val personDao: PersonDao)(implicit val ec: ExecutionContext) {

    @GET
    @Produces(Array[String](MediaType.APPLICATION_JSON))
    def allPersons(): mutiny.Uni[Seq[Map[String, Any]]] = {
        personDao.allPersons().toUni
    }

}
