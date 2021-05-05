package io.aethibo.repositories

import io.aethibo.entities.response.Thought

interface MainRepository {

    suspend fun getAllThoughts(): List<Thought>
    suspend fun getThought(id: String): Thought?
    suspend fun addThought(draft: Thought): Thought
    suspend fun removeThought(id: String): Boolean
    suspend fun updateThought(id: String, thought: Thought): Boolean
}