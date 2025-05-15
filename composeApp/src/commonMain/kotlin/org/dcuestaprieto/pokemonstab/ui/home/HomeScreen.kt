package org.dcuestaprieto.pokemonstab.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.dcuestaprieto.pokemonstab.ui.core.navigation.Routes
import org.dcuestaprieto.pokemonstab.ui.core.navigation.bottomnavigation.BottomBarItem
import org.dcuestaprieto.pokemonstab.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen() {
    /*
    pantalla que gestiona la navegacion de abajo
    se carga home como destino principal y esta es la que maneja dentro de home si mostrar una
    screen u otra
     */

    val items = listOf(
        BottomBarItem.Types(),
        BottomBarItem.Favourites()
    )
    val navController = rememberNavController()

    // list of routes where we want to show the BottomNavigation
    val bottomBarRoutes = setOf(
        Routes.Types.route,
        Routes.Favourites.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    //bottomBar es un parametro de scaffold que nos permite organizar mejor la vista para fijar el contenido abajo
    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigation(items, navController)
            }
        }
    ) { innerPadding ->
        NavigationBottomWrapper(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
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
                alwaysShowLabel = false,
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