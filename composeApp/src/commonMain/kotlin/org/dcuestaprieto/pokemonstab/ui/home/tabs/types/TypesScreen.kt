package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TypesScreen(navController: NavHostController) {
    val typesViewModel = koinViewModel<TypesViewModel>()
    val state by typesViewModel.state.collectAsState()

    state.typesList?.let {
        LazyColumn {
            items(it) { type ->
                Box {
                    AsyncImage(
                        model = type.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .height(40.dp)
                            .clickable {
                                //TODO:change hardcoded detail route
                                navController.navigate("detail/${type.name}")
                            }
                    )
                }
            }
        }
    }
}