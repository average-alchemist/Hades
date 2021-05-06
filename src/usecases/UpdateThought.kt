package io.aethibo.usecases

import io.aethibo.entities.response.Thought
import io.aethibo.repositories.MainRepository

interface UpdateThoughtUseCase {
    suspend operator fun invoke(id: Int, thought: Thought): Boolean
}

class UpdateThoughtUseCaseImpl(private val repository: MainRepository) : UpdateThoughtUseCase {

    override suspend operator fun invoke(id: Int, thought: Thought): Boolean =
        repository.updateThought(id, thought)
}