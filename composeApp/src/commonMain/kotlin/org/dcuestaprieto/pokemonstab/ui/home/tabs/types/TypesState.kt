package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import org.dcuestaprieto.pokemonstab.data.remote.response.types.TypeDetailUrlResponse
import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

data class TypesState(
    val randomType: TypeModel? = null,
    val typesList: List<TypeDetailUrlResponse>? = null
)