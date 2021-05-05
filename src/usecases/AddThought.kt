package io.aethibo.usecases

import io.aethibo.entities.response.Thought
import io.aethibo.repositories.MainRepository

interface AddThoughtUseCase {
    suspend operator fun invoke(thought: Thought): Thought
}

class AddThoughtUseCaseImpl(private val repository: MainRepository) : AddThoughtUseCase {

    override suspend fun invoke(thought: Thought): Thought =
        repository.addThought(thought)
}