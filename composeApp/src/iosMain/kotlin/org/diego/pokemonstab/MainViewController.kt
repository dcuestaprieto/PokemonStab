package org.diego.pokemonstab

import androidx.compose.ui.window.ComposeUIViewController
import org.diego.pokemonstab.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }