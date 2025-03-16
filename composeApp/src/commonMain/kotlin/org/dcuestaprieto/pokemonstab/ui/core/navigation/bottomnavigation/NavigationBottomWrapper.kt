package org.dcuestaprieto.pokemonstab.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.dcuestaprieto.pokemonstab.ui.core.navigation.Routes
import org.dcuestaprieto.pokemonstab.ui.home.tabs.favourites.FavouritesScreen
import org.dcuestaprieto.pokemonstab.ui.home.tabs.types.TypesScreen
import org.dcuestaprieto.pokemonstab.ui.types.TypeDetail

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Types.route) {
        composable(route = Routes.Types.route) {
            TypesScreen(navController)
        }
        composable(route = Routes.Favourites.route) {
            FavouritesScreen()
        }
        composable(
            route = Routes.TypePage.route,
            arguments = listOf(navArgument("typeName") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val typeName = backStackEntry.savedStateHandle.get<String>("typeName") ?: "Not Found"
            TypeDetail(typeName)

        }
    }
}