package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable
import org.dcuestaprieto.pokemonstab.domain.model.NotFullTypeModel

@Serializable
data class TypeDetailUrlResponse(
    val id: Int,
    val name: String,
    val imageUrl: String
) {
    fun toDomain(): NotFullTypeModel {
        return NotFullTypeModel(
            name = name,
            imageUrl = imageUrl
        )
    }
}