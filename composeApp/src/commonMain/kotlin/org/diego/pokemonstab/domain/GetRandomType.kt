package org.diego.pokemonstab.domain

class GetRandomType(private val repository: TypesRepository) {
    suspend operator fun invoke(){
        val random = 7
        repository.getTypeById(random.toString())
    }
}