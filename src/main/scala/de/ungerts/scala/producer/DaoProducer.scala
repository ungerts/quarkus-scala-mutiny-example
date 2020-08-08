package de.ungerts.scala.producer

import de.ungerts.scala.dao.PersonDao
import io.quarkus.arc.DefaultBean
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Singleton
import javax.sql.DataSource
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.{Logger, LoggerFactory}
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext


@ApplicationScoped
class DaoProducer {

    val logger: Logger = LoggerFactory.getLogger("SlickProducer")

    @DefaultBean
    @Singleton
    @Produces
    def personDao(dataSource: DataSource, @ConfigProperty(name = "quarkus.datasource.jdbc.max-size", defaultValue = "10") poolSize: Int)(implicit ec: ExecutionContext): PersonDao = {
        logger.info("Create Slick")
        val db = Database.forDataSource(dataSource, Some(poolSize), AsyncExecutor("Slick-Executor", numThreads=poolSize, queueSize=1000))
        new PersonDao(db)
    }

}
