package org.diego.pokemonstab.di

import org.diego.pokemonstab.ui.home.tabs.favourites.FavouritesViewModel
import org.diego.pokemonstab.ui.home.tabs.types.TypesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::TypesViewModel)
    viewModelOf(::FavouritesViewModel)
}