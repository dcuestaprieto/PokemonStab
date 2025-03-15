package org.diego.pokemonstab

import android.app.Application
import org.diego.pokemonstab.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class PokemonStabApp: Application() {
    /**
     * On the PokemonStab app creation, this method it's calleds
     * */
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@PokemonStabApp)
        }
    }
}