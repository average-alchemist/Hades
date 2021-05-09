package io.aethibo.usecases

import io.aethibo.entities.request.SignInDraft
import io.aethibo.entities.response.User
import io.aethibo.repositories.MainRepository

interface SignInUserUseCase {
    suspend operator fun invoke(draft: SignInDraft): User?
}

class SignInUserUseCaseImpl(private val repository: MainRepository) : SignInUserUseCase {

    override suspend fun invoke(draft: SignInDraft): User? =
        repository.getUser(draft)
}