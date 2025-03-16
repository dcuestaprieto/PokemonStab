package org.dcuestaprieto.pokemonstab.ui.core.navigation

import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    //BottomNav
    data object Types : Routes("type")
    data object Favourites : Routes("favourites")
    //TypePage
    data object TypePage: Routes("detail/{typeName}")
}