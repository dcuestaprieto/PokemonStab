package org.diego.pokemonstab.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.diego.pokemonstab.ui.core.navigation.Routes
import org.diego.pokemonstab.ui.home.tabs.favourites.FavouritesScreen
import org.diego.pokemonstab.ui.home.tabs.types.TypesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.Types.route){
        composable(route = Routes.Types.route) {
            TypesScreen()
        }
        composable(route = Routes.Favourites.route) {
            FavouritesScreen()
        }
    }

}