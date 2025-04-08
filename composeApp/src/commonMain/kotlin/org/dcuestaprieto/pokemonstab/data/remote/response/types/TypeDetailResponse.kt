package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable

@Serializable
data class TypeDetailResponse(
    val id: Int,
    val name: String,
    val sprites: Map<String, Map<String, SpriteEntry>>
)