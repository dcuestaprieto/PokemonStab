package org.dcuestaprieto.pokemonstab.domain

import org.dcuestaprieto.pokemonstab.domain.model.NotFullTypeModel

class GetRandomType(private val repository: TypesRepository) {
    suspend operator fun invoke():NotFullTypeModel{
        val random = 2
        return repository.getTypeByIdFake(random.toString())
    }
}