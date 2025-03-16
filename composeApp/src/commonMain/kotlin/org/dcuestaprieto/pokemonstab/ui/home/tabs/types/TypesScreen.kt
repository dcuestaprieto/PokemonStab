package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.dcuestaprieto.pokemonstab.ui.core.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TypesScreen(navController: NavHostController) {
    val typesViewModel = koinViewModel<TypesViewModel>()
    val state by typesViewModel.state.collectAsState()


    state.typesList?.let {
        LazyColumn {
            items(it) { type ->
                Text(
                    text = type.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //TODO:change hardcoded detail route
                            navController.navigate("detail/${type.name}")
                        }
                )
            }
        }
    }
}