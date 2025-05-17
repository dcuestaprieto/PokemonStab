package org.dcuestaprieto.pokemonstab.data

import org.dcuestaprieto.pokemonstab.data.remote.ApiService
import org.dcuestaprieto.pokemonstab.domain.TypesRepository
import org.dcuestaprieto.pokemonstab.domain.model.AllTypesModel
import org.dcuestaprieto.pokemonstab.domain.model.NotFullTypeModel

class PokeApiTypeRepository(private val api: ApiService) : TypesRepository {
    override suspend fun getTypeByIdFake(id: String): NotFullTypeModel {
        return api.getTypeById(id).toDomain()
    }
    override suspend fun getAllTypes(): AllTypesModel {
        return api.getAllTypes().toDomain()
    }
}