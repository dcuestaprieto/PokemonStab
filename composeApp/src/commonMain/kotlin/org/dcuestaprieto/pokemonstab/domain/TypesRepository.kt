package org.dcuestaprieto.pokemonstab.domain

import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

interface TypesRepository {
    suspend fun getTypeById(id: String): TypeModel
}