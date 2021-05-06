package io.aethibo.usecases

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.repositories.MainRepository

interface UpdateThoughtUseCase {
    suspend operator fun invoke(id: String, draft: ThoughtDraft): Boolean
}

class UpdateThoughtUseCaseImpl(private val repository: MainRepository) : UpdateThoughtUseCase {

    override suspend operator fun invoke(id: String, draft: ThoughtDraft): Boolean =
        repository.updateThought(id, draft)
}