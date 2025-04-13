package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import pokemonstab.composeapp.generated.resources.Res
import pokemonstab.composeapp.generated.resources.x_cross_delete

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TypesScreen(navController: NavHostController) {
    val typesViewModel = koinViewModel<TypesViewModel>()
    val state by typesViewModel.state.collectAsState()
    val selectedTypes = remember { mutableStateListOf<Int>() }

    state.typesList?.let {
        LazyColumn {
            items(it) { type ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .height(40.dp)
                        .combinedClickable(
                            onClick = {
                                //if the type is on the list we remove it
                                if (selectedTypes.contains(type.id)) {
                                    selectedTypes.remove(type.id)
                                } else {
                                    //if the list it's empty, the user only wants to check one type
                                    if (selectedTypes.isEmpty()) {
                                        //TODO:change hardcoded detail route
                                        navController.navigate("detail/${type.name}")
                                    } else {
                                        // add the selected type only if it's not added yet and if the size is greater than 2
                                        if (selectedTypes.size < 2) {
                                            selectedTypes.add(type.id)
                                        }
                                    }
                                }
                            },
                            onLongClick = {
                                // add the selected type only if it's not added yet and if the size is greater than 2
                                if (!selectedTypes.contains(type.id) && selectedTypes.size < 2) {
                                    selectedTypes.add(type.id)
                                }
                            }
                        )
                ) {
                    AsyncImage(
                        model = type.imageUrl,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )

                    if (selectedTypes.contains(type.id)) {
                        Image(
                            painter = painterResource(Res.drawable.x_cross_delete),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    selectedTypes.remove(type.id)
                                }
                                .padding(4.dp)
                                .size(20.dp)
                        )
                    }
                }
            }
        }
    }
}