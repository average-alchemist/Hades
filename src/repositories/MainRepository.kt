package io.aethibo.repositories

import io.aethibo.entities.request.SignInDraft
import io.aethibo.entities.request.SignUpDraft
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.entities.response.User

interface MainRepository {

    suspend fun getAllThoughts(): List<Thought>
    suspend fun getThought(id: String): Thought?
    suspend fun addThought(draft: ThoughtDraft): Thought?
    suspend fun removeThought(id: String): Boolean
    suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean

    suspend fun createUser(draft: SignUpDraft): User?
    suspend fun getUserById(userId: String): User?
    suspend fun getUser(draft: SignInDraft): User?
}