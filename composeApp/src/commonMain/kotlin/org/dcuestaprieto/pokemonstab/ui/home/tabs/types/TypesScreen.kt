package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TypesScreen() {
    val typesViewModel = koinViewModel<TypesViewModel>()
    val state by typesViewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()){
        state.randomType?.let {
            Text(it.name)
        }
    }
}