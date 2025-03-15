package org.dcuestaprieto.pokemonstab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform