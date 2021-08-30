package io.aethibo.routes

import io.aethibo.entities.request.SignUpDraft
import io.aethibo.entities.response.User
import io.aethibo.usecases.SignUpUserUseCase
import io.aethibo.utils.RouteUtils
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Route.registerRoute() {

    val signUpUser: SignUpUserUseCase by inject()

    /**
     * Register/Create user profile
     */
    post(RouteUtils.REGISTER) {
        val draft: SignUpDraft = call.receive()
        val saveUser: User? = signUpUser.invoke(draft)

        saveUser
            ?.let { call.respond(it) }
            ?: call.respond(HttpStatusCode.BadRequest, "Cannot create user. Please try again")
    }
}