package org.example.project

import de.jensklingenberg.ktorfit.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodo(): List<Response>
}
