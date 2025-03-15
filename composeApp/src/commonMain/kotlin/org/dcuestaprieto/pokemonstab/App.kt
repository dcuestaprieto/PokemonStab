package org.dcuestaprieto.pokemonstab

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.dcuestaprieto.pokemonstab.ui.core.navigation.NavigationWrapper

@Composable
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}