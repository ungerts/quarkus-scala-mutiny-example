package de.ungerts.scala.producer

import io.quarkus.arc.DefaultBean
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Singleton
import org.eclipse.microprofile.context.ManagedExecutor

import scala.concurrent.ExecutionContext

@ApplicationScoped
class ExecutionContextProducer {

    @DefaultBean
    @Singleton
    @Produces
    def executionContext(managedExecutor: ManagedExecutor): ExecutionContext = {
        ExecutionContext.fromExecutor(managedExecutor)
    }

}
