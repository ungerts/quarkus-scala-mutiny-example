package de.ungerts.scala.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton

@Singleton
class RegisterScalaModuleCustomizer extends ObjectMapperCustomizer {

    override def customize(objectMapper: ObjectMapper): Unit = {
        objectMapper.registerModule(DefaultScalaModule)
    }

}
