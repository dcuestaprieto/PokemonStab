package org.diego.pokemonstab

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.diego.pokemonstab.ui.core.navigation.NavigationWrapper
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}