package org.diego.pokemonstab.ui.home.tabs.types

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
fun TypesScreen(){
    val typesViewModel = koinViewModel<TypesViewModel>()
    Box(Modifier.fillMaxSize().background(Color.Yellow))
}