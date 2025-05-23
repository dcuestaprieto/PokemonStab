package org.dcuestaprieto.pokemonstab.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json
import org.dcuestaprieto.pokemonstab.data.remote.response.types.AllTypesResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.SpriteEntry
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailUrlResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeListResponse
import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeResponse
import kotlin.random.Random

const val PREFERRED_GENERATION = "generation-ix"
const val PREFERRED_GENERATION_GAME = "scarlet-violet"
const val PREFIX_URL_POKEAPI = "/api/v2/"

// commonMain
object JsonConfig {
    val default = Json {
        ignoreUnknownKeys = true      // ¡ignora las keys que no tengas en tu data class!
        coerceInputValues = true      // (opcional) rellena con valores por defecto si faltan campos
        isLenient = true              // (opcional) tolera comillas simples, comas finales…
    }
}

class ApiService(private val client: HttpClient) {
    suspend fun getTypeById(id: String): TypeResponse {
        //return client.get("${PREFIX_URL_POKEAPI}type/$id").body<TypeResponse>()
        val rawResponse = client.get("${PREFIX_URL_POKEAPI}type/$id").bodyAsText()
        return JsonConfig.default.decodeFromString(rawResponse)
    }

    suspend fun getAllTypes(): AllTypesResponse = coroutineScope {
        //todo: create class for urls
        val rawTypeList = client.get("${PREFIX_URL_POKEAPI}type").bodyAsText()
        val typeListResponse = JsonConfig.default.decodeFromString<TypeListResponse>(rawTypeList)
        // 📦 Usamos async para realizar las llamadas a cada detalle en paralelo
        val typesDeferred = typeListResponse.results.map { entry ->
            async {
                //val detailUrl = entry.url.removeSuffix("/")
                //val typeDetail: TypeDetailResponse = client.get(detailUrl).body()
                //val typeDetail: TypeDetailResponse = client.get(entry.url).body()
                val cleanUrl = entry.url.removeSuffix("/")
                val rawResponse = client.get(cleanUrl).bodyAsText()
                val typeDetail = JsonConfig.default.decodeFromString<TypeDetailResponse>(rawResponse)
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
    val generationIxMap = sprites[PREFERRED_GENERATION]
    //search for the preferred game and if exists return it's url type image
    generationIxMap?.get(PREFERRED_GENERATION_GAME)?.nameIcon?.let { return it }
    //else logic for return a random url type image
    val urls: List<String> = sprites.flatMap { (_, generationMap) ->
        generationMap.values.mapNotNull { it?.nameIcon }
    }

    if (urls.isEmpty()) return null

    val randomIndex = Random.Default.nextInt(urls.size)
    return urls[randomIndex]
}