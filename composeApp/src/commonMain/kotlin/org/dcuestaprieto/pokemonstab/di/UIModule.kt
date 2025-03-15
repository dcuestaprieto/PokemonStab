package org.dcuestaprieto.pokemonstab.di

import org.dcuestaprieto.pokemonstab.ui.home.tabs.favourites.FavouritesViewModel
import org.dcuestaprieto.pokemonstab.ui.home.tabs.types.TypesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::TypesViewModel)
    viewModelOf(::FavouritesViewModel)
}