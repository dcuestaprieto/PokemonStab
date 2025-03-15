package org.diego.pokemonstab.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.diego.pokemonstab.data.remote.response.TypeResponse

class ApiService(private val client: HttpClient) {
    suspend fun getTypeById(id: String): TypeResponse {
        return client.get("/type/$id").body<TypeResponse>()
    }
}