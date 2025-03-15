package org.diego.pokemonstab.di

import org.diego.pokemonstab.domain.GetRandomType
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomType)
}