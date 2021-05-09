package io.aethibo.entities.request

data class SignInDraft(
    val email: String,
    val passwordHash: String? = null
)
