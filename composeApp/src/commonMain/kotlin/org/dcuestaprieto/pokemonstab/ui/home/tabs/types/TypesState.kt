package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailUrlResponse

data class TypesState(
    val typesList: List<TypeDetailUrlResponse>? = null
)