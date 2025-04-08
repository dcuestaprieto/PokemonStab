package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpriteEntry(
    @SerialName("name_icon")
    val nameIcon: String? = null
)