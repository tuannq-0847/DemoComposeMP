package org.example.project

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    private val ktorfit = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(httpClient)
        .build()


    fun api(): ApiService {
        return ktorfit.createApiService()  //generated
    }
}