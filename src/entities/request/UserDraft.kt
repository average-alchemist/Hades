package io.aethibo.entities.request

data class UserDraft(
    val id: String,
    val email: String,
    val displayName: String,
    val password: String
)
