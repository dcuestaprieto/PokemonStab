package org.dcuestaprieto.pokemonstab.domain

import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

class GetRandomType(private val repository: TypesRepository) {
    suspend operator fun invoke():TypeModel{
        val random = 2
        return repository.getTypeById(random.toString())
    }
}