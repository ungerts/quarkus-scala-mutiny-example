package de.ungerts.scala.dao

import de.ungerts.scala.entity.Person
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.{ExecutionContext, Future}

class PersonDao(val db: H2Profile.backend.DatabaseDef)(implicit val ec: ExecutionContext) {

    def nameById(id: Int): Future[String] = {
        val query = sql"SELECT name FROM PERSON where id = $id".as[String].headOption
        for {
            result <- db.run(query)
        } yield {
            result match {
                case Some(name) => name
                case _ => "Nobody"
            }
        }
    }

    def allPersons(): Future[Seq[Map[String, Any]]] = {
        val query = sql"SELECT id, name FROM PERSON".as[(Int, String)]
        for {
            result <- db.run(query)
        } yield {
            result map (tuple => Map("id" -> tuple._1, "name" -> tuple._2))
        }
    }

}
