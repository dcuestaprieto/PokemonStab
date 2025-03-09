package org.diego.pokemonstab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform