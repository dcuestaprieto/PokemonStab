package org.diego.pokemonstab.ui.core.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.diego.pokemonstab.ui.core.navigation.Routes

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Types(
        override val route: String = Routes.Types.route,
        override val title: String = "Types",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, null)
        }
    ) : BottomBarItem()

    data class Favourites(
        override val route: String = Routes.Favourites.route,
        override val title: String = "Favourites",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, null)
        }
    ) : BottomBarItem()

}