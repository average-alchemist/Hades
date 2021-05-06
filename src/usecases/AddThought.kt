package io.aethibo.usecases

import io.aethibo.entities.response.Thought
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.repositories.MainRepository

interface AddThoughtUseCase {
    suspend operator fun invoke(draft: ThoughtDraft): Thought?
}

class AddThoughtUseCaseImpl(private val repository: MainRepository) : AddThoughtUseCase {

    override suspend fun invoke(draft: ThoughtDraft): Thought? =
        repository.addThought(draft)
}