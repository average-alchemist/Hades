package io.aethibo.usecases

import io.aethibo.entities.request.SignUpDraft
import io.aethibo.entities.response.User
import io.aethibo.repositories.MainRepository

interface SignUpUserUseCase {
    suspend operator fun invoke(draft: SignUpDraft): User?
}

class SignUpUserUseCaseImpl(private val repository: MainRepository) : SignUpUserUseCase {

    override suspend fun invoke(draft: SignUpDraft): User? =
        repository.createUser(draft)
}