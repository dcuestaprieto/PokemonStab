package org.diego.pokemonstab.data

import org.diego.pokemonstab.data.remote.ApiService
import org.diego.pokemonstab.domain.TypesRepository
import org.diego.pokemonstab.domain.model.TypeModel

class PokeApiTypeRepository(private val api: ApiService) : TypesRepository {
    override suspend fun getTypeById(id: String): TypeModel {
        return api.getTypeById(id).toDomain()
    }
}