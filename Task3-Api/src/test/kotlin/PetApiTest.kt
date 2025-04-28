// import io.kotest.core.spec.style.StringSpec
// import io.kotest.matchers.shouldBe
// import io.ktor.client.*
// import io.ktor.client.call.*
// import io.ktor.client.engine.cio.*
// import io.ktor.client.plugins.contentnegotiation.*
// import io.ktor.client.request.*
// import io.ktor.http.*
// import io.ktor.serialization.kotlinx.json.*
// import kotlinx.coroutines.*
// import kotlinx.serialization.*

// class PetApiTest : StringSpec({

//     val client = HttpClient(CIO) {
//         install(ContentNegotiation) {
//             json()
//         }
//     }

//     "should get a pet by id" {
//         val response = runBlocking {
//             client.get("https://petstore.swagger.io/v2/pet/1")
//         }
        
//         // val body = runBlocking { response.bodyAsText() }
//         val body = runBlocking { response.body<String>() }
//         println("Response body: $body")

//         response.status shouldBe HttpStatusCode.OK

//     }

//     afterSpec {
//         client.close()
//     }
// })

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Pet(
    val id: Long,
    val name: String,
    val status: String? = null
)

class PetApiTest : StringSpec() {

    private lateinit var client: HttpClient

    override suspend fun beforeSpec(spec: Spec) {
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    override suspend fun afterSpec(spec: Spec) {
        client.close()
    }

    init {
        "should retrieve an existing pet by ID successfully" {
            val petId = 1 // Known existing pet
            val response: HttpResponse = client.get("https://petstore.swagger.io/v2/pet/$petId")

            response.status shouldBe HttpStatusCode.OK

            val pet: Pet = response.body()
            pet.id shouldBe petId
        }

        "should fail to retrieve a non-existing pet" {
            val nonExistingPetId = 9999999999999999 // Assume non-existent
            val response: HttpResponse = client.get("https://petstore.swagger.io/v2/pet/$nonExistingPetId")

            response.status shouldBe HttpStatusCode.NotFound
        }

        "should create a new pet successfully" {
            val newPet = Pet(id = System.currentTimeMillis(), name = "Fluffy", status = "available")
            val response: HttpResponse = client.post("https://petstore.swagger.io/v2/pet") {
                contentType(ContentType.Application.Json)
                setBody(newPet)
            }

            response.status shouldBe HttpStatusCode.OK

            val createdPet: Pet = response.body()
            createdPet.name shouldBe "Fluffy"
            createdPet.status shouldBe "available"
        }

        "should update an existing pet successfully" {
            val updatedPet = Pet(id = 9222968140497192000, name = "UpdatedName", status = "sold")
            val response: HttpResponse = client.put("https://petstore.swagger.io/v2/pet") {
                contentType(ContentType.Application.Json)
                setBody(updatedPet)
            }

            response.status shouldBe HttpStatusCode.OK

            val pet: Pet = response.body()
            pet.name shouldBe "UpdatedName"
            pet.status shouldBe "sold"
        }

        "should delete an existing pet successfully" {
            val petIdToDelete = 3 // Use an existing one for testing
            val response: HttpResponse = client.delete("https://petstore.swagger.io/v2/pet/$petIdToDelete")

            // API sometimes returns 200 or 404 if already deleted, so both acceptable
            (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.NotFound) shouldBe true
        }

        "should fail to create pet with invalid payload" {
            val response: HttpResponse = client.post("https://petstore.swagger.io/v2/pet") {
                contentType(ContentType.Application.Json)
                setBody("{ invalid json }") // bad request body
            }

            response.status shouldBe HttpStatusCode.BadRequest
        }
    }
}