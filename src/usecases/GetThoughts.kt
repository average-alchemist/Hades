package io.aethibo.usecases

import io.aethibo.entities.response.Thought
import io.aethibo.repositories.MainRepository

interface GetThoughtsUseCase {
    suspend operator fun invoke(): List<Thought>
}

class GetThoughtsUseCaseImpl(private val repository: MainRepository) : GetThoughtsUseCase {

    override suspend operator fun invoke(): List<Thought> =
        repository.getAllThoughts()
}