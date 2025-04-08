package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable

@Serializable
data class TypeListResponse(
    val results: List<TypeEntry>
)