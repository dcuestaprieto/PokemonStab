package org.dcuestaprieto.pokemonstab.ui.home.tabs.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun FavouritesScreen() {
    val typesViewModel = koinViewModel<FavouritesViewModel>()
    Box(Modifier.fillMaxSize().background(Color.Blue))
}