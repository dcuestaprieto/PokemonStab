package org.dcuestaprieto.pokemonstab.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.dcuestaprieto.pokemonstab.data.PokeApiTypeRepository
import org.dcuestaprieto.pokemonstab.data.remote.ApiService
import org.dcuestaprieto.pokemonstab.domain.TypesRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                //ignoreUnknownKeys si en la data de respuesta llega un dato que no está establecido lo ignora
                //y así hacemos que no se rompa la aplicacion
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "https://pokeapi.co/api/v2/"
                    //parameters.append("key","value")
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory<TypesRepository> {PokeApiTypeRepository(get())}
}