package org.dcuestaprieto.pokemonstab.di

import org.dcuestaprieto.pokemonstab.domain.GetRandomType
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomType)
}