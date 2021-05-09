package io.aethibo.entities.response

import io.ktor.auth.*
import java.io.Serializable

data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val passwordHash: String
) : Serializable, Principal
