package io.aethibo.repositories

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.entities.response.User

interface MainRepository {

    suspend fun getAllThoughts(): List<Thought>
    suspend fun getThought(id: String): Thought?
    suspend fun addThought(draft: ThoughtDraft): Thought?
    suspend fun removeThought(id: String): Boolean
    suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean

    suspend fun addUser(user: User): User?
    suspend fun getUserById(userId: String): User?
    suspend fun user(userId: String, hash: String? = null): User?
}