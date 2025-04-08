package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable

@Serializable
data class TypeEntry(
    val name: String,
    val url: String
)