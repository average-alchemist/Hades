package io.aethibo.repositories

import io.aethibo.entities.response.Thought

interface MainRepository {

    suspend fun getAllThoughts(): List<Thought>
    suspend fun getThought(id: Int): Thought?
    suspend fun addThought(draft: Thought): Thought?
    suspend fun removeThought(id: Int): Boolean
    suspend fun updateThought(id: Int, thought: Thought): Boolean
}