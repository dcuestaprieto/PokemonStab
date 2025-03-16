package org.dcuestaprieto.pokemonstab.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.dcuestaprieto.pokemonstab.ui.core.navigation.bottomnavigation.BottomBarItem
import org.dcuestaprieto.pokemonstab.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen() {
    /*
    pantalla que gestiona la navegacion de abajo
    se carga home como destino principal y esta es la que maneja dentro de home si mostrar una
    screen u otra
     */

    val items = listOf(BottomBarItem.Types(), BottomBarItem.Favourites())
    val navController = rememberNavController()
    //bottomBar es un parametro de scaffold que nos permite organizar mejor la vista para fijar el contenido abajo
    Scaffold(bottomBar = { BottomNavigation(items, navController) }) {
        Box {
            NavigationBottomWrapper(navController)
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }
}