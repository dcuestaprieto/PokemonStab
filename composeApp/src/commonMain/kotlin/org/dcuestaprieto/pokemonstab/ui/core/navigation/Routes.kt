package org.dcuestaprieto.pokemonstab.ui.core.navigation

sealed class Routes(val route:String){
    data object Home:Routes("home")

    //BottomNav
    data object Types:Routes("type")
    data object Favourites:Routes("favourites")
}