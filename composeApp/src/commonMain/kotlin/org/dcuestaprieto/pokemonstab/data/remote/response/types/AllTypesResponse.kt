package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable
import org.dcuestaprieto.pokemonstab.domain.model.AllTypesModel

@Serializable
data class AllTypesResponse(
    val results: List<TypeResponse>
) {
    fun toDomain(): AllTypesModel {
        return AllTypesModel(
            results = results.map { it.toDomain() }
        )
    }
}