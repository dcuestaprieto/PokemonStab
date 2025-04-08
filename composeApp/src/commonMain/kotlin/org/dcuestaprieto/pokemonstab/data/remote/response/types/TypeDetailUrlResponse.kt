package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable
import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

@Serializable
data class TypeDetailUrlResponse(
    val id: Int,
    val name: String,
    val imageUrl: String
) {
    fun toDomain(): TypeModel {
        return TypeModel(
            name = name,
            imageUrl = imageUrl
        )
    }
}