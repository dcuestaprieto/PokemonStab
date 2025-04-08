package org.dcuestaprieto.pokemonstab.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.dcuestaprieto.pokemonstab.data.remote.response.types.AllTypesResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.SpriteEntry
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailUrlResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeListResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeResponse
import kotlin.random.Random

class ApiService(private val client: HttpClient) {
    suspend fun getTypeById(id: String): TypeResponse {
        return client.get("/type/$id").body<TypeResponse>()
    }
    suspend fun getAllTypes(): AllTypesResponse = coroutineScope {
        val typeListResponse: TypeListResponse = client.get("/type/").body()

        // 📦 Usamos async para realizar las llamadas a cada detalle en paralelo
        val typesDeferred = typeListResponse.results.map { entry ->
            async {
                val typeDetail: TypeDetailResponse = client.get(entry.url).body()

                val currentSprite = getRandomSpriteUrl(typeDetail.sprites)

                TypeDetailUrlResponse(
                    id = typeDetail.id,
                    name = typeDetail.name,
                    imageUrl = currentSprite!!
                )
            }
        }

        AllTypesResponse(results = typesDeferred.awaitAll())
    }
}

private fun getRandomSpriteUrl(sprites: Map<String, Map<String, SpriteEntry?>>): String? {
    val urls: List<String> = sprites.flatMap { (_, generationMap) ->
        generationMap.values.mapNotNull { it?.nameIcon }
    }

    if (urls.isEmpty()) return null

    val randomIndex = Random.Default.nextInt(urls.size)
    return urls[randomIndex]
}