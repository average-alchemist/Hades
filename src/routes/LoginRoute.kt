package io.aethibo.routes

import io.aethibo.repositories.MainRepository
import io.aethibo.utils.JwtService
import io.aethibo.utils.RouteUtils.Login
import io.aethibo.utils.hash
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Route.loginRoute(repository: MainRepository, service: JwtService) {

    post<Login> {
        val params = call.receiveParameters()

        val userId = params["id"] ?: return@post
        val password = params["password"] ?: return@post

        val user = repository.user(userId, hash(password))

        println("User: $user")

        if (user != null) {
            val token = service.generateToken(user)
            call.respondText(token)
        } else call.respondText("Invalid User")
    }
}