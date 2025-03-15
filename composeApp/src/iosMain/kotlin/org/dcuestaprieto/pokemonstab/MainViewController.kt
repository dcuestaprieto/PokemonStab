package org.dcuestaprieto.pokemonstab

import androidx.compose.ui.window.ComposeUIViewController
import org.dcuestaprieto.pokemonstab.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }