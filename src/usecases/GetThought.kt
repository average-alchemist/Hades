package io.aethibo.usecases

import io.aethibo.entities.response.Thought
import io.aethibo.repositories.MainRepository

interface GetThoughtUseCase {
    suspend operator fun invoke(id: Int): Thought?
}

class GetThoughtUseCaseImpl(private val repository: MainRepository) : GetThoughtUseCase {

    override suspend operator fun invoke(id: Int) =
        repository.getThought(id)
}