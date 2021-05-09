package io.aethibo.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.aethibo.entities.response.User
import java.util.*

object JwtService {

    private const val issuer = "thoughtsapp"
    private const val jwtSecret = "kZnzR7iw463VyysmS9qln"
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withExpiresAt(expiresAt())
        .sign(algorithm)

    private fun expiresAt(): Date = Date(System.currentTimeMillis() + 3_600_000 * 24) // 24 hours
}