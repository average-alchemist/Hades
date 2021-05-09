package io.aethibo.routes

import io.aethibo.entities.request.SignInDraft
import io.aethibo.entities.response.LoginResponse
import io.aethibo.usecases.SignInUserUseCase
import io.aethibo.utils.JwtService
import io.aethibo.utils.RouteUtils.Login
import io.aethibo.utils.hash
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Route.loginRoute(service: JwtService) {

    val signInUser: SignInUserUseCase by inject()

    post<Login> {
        val params = call.receiveParameters()

        val email = params["email"] ?: return@post
        val password = params["password"] ?: return@post

        val draft = SignInDraft(email, hash(password))

        val user = signInUser.invoke(draft)

        if (user != null) {
            val token = service.generateToken(user)
            call.respond(LoginResponse("Bearer", token))
        } else call.respond(HttpStatusCode.BadRequest, "Cannot sign in into this account. Please try again.")
    }
}