package org.dcuestaprieto.pokemonstab.ui.home.tabs.types

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
fun TypesScreen(navController: NavHostController) {
    val typesViewModel = koinViewModel<TypesViewModel>()
    val state by typesViewModel.state.collectAsState()
    val selectedTypes = remember { mutableStateListOf<Int>() }

    Scaffold(
        bottomBar = {
            // show the button if there are 1 or 2 types selected
            if (selectedTypes.isNotEmpty() && selectedTypes.size <= 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(onClick = {
                        //save the selected types list
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("selectedTypes", selectedTypes.toIntArray())
                        navController.navigate("detail")
                    }) {
                        Text("Continuar")
                    }
                }
            }
        }
    ) { innerPadding ->
        state.typesList?.let {
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(it) { type ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                            // guardamos la lista de 1 ó 2 IDs
                                            navController.currentBackStackEntry
                                                ?.savedStateHandle
                                                ?.set("selectedTypes", listOf(type.id).toIntArray())
                                            //TODO:change hardcoded detail route
                                            navController.navigate("detail")
                                        } else if (selectedTypes.size < 2) {
                                            // add the selected type only if it's not added yet and if the size is greater than 2
                                            selectedTypes.add(type.id)
                                        }
                                    }
                                },
                                onLongClick = {
                                    if (!selectedTypes.contains(type.id) && selectedTypes.size < 2) {
                                        selectedTypes.add(type.id)
                                    }
                                }
                            )
                    ) {
                        AsyncImage(
                            model = type.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )

                        if (selectedTypes.contains(type.id)) {
                            Image(
                                painter = painterResource(Res.drawable.x_cross_delete),
                                contentDescription = null,
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
}