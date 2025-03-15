package org.dcuestaprieto.pokemonstab.data

import org.dcuestaprieto.pokemonstab.data.remote.ApiService
import org.dcuestaprieto.pokemonstab.domain.TypesRepository
import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

class PokeApiTypeRepository(private val api: ApiService) : TypesRepository {
    override suspend fun getTypeById(id: String): TypeModel {
        return api.getTypeById(id).toDomain()
    }
}