package io.aethibo.usecases

import io.aethibo.repositories.MainRepository

interface RemoveThoughtUseCase {
    suspend operator fun invoke(id: Int): Boolean
}

class RemoveThoughtUseCaseImpl(private val repository: MainRepository) : RemoveThoughtUseCase {

    override suspend operator fun invoke(id: Int): Boolean =
        repository.removeThought(id)
}