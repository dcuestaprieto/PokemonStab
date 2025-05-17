package org.dcuestaprieto.pokemonstab.domain

import org.dcuestaprieto.pokemonstab.domain.model.AllTypesModel
import org.dcuestaprieto.pokemonstab.domain.model.NotFullTypeModel

interface TypesRepository {
    suspend fun getTypeByIdFake(id: String): NotFullTypeModel
    suspend fun getAllTypes(): AllTypesModel
}