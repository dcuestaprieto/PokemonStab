package org.dcuestaprieto.pokemonstab.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.dcuestaprieto.pokemonstab.data.remote.response.types.AllTypesResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeResponse

class ApiService(private val client: HttpClient) {
    suspend fun getTypeById(id: String): TypeResponse {
        return client.get("/type/$id").body<TypeResponse>()
    }
    suspend fun getAllTypes(): AllTypesResponse {
        return client.get("/type/").body<AllTypesResponse>()
    }
}