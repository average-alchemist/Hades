package io.aethibo.routes

import io.aethibo.entities.response.User
import io.aethibo.repositories.MainRepository
import io.aethibo.utils.RouteUtils.Register
import io.aethibo.utils.hash
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Route.registerRoute(repository: MainRepository) {

    post<Register> {
        val params = call.receiveParameters()

        val userId: String = params["id"].toString() ?: return@post
        val email: String = params["email"].toString() ?: return@post
        val displayName: String = params["displayName"].toString() ?: return@post
        val password: String = params["password"].toString() ?: return@post

        val user = User(userId, email, displayName, hash(password))
        val saveUser: User? = repository.addUser(user)

        saveUser
            ?.let { call.respond(it) }
            ?: call.respond(HttpStatusCode.BadRequest, "Cannot create user. Please try again")
    }
}