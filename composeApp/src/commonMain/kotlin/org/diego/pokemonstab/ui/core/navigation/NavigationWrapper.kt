package org.diego.pokemonstab.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.diego.pokemonstab.ui.home.HomeScreen
import org.diego.pokemonstab.ui.types.TwoViewsWithIndicator

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.Home.route){
        composable(route = Routes.Home.route){
            //TwoViewsWithIndicator()
            HomeScreen()
        }
    }
}