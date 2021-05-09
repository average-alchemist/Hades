package io.aethibo.usecases

import io.aethibo.entities.response.User
import io.aethibo.repositories.MainRepository

interface GetUserByIdUseCase {
    suspend operator fun invoke(id: String): User?
}

class GetUserByIdUseCaseImpl(private val repository: MainRepository) : GetUserByIdUseCase {

    override suspend fun invoke(id: String): User? =
        repository.getUserById(id)
}