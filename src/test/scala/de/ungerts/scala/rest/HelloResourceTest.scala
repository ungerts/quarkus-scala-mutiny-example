package de.ungerts.scala.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class HelloResourceTest {

    @Test
    def testHelloMutinyEndpoint(): Unit = {
        given()
          .`when`().get("/hello/mutiny")
          .then()
             .statusCode(200)
             .body(`is`("Hello Mutiny!"))
    }

    @Test
    def testHelloScalaEndpoint(): Unit = {
        given()
            .`when`().get("/hello/scala")
            .then()
            .statusCode(200)
            .body(`is`("Hello Scala!"))
    }

}
