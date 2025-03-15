package org.diego.pokemonstab.domain

import org.diego.pokemonstab.domain.model.TypeModel

interface TypesRepository {
    suspend fun getTypeById(id: String): TypeModel
}