package io.aethibo.routes

import io.aethibo.usecases.GetUserByIdUseCase
import io.aethibo.usecases.RemoveThoughtUseCase
import io.aethibo.utils.RouteUtils
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.deleteThought() {

    val getUser: GetUserByIdUseCase by inject()
    val removeThought: RemoveThoughtUseCase by inject()

    /**
     * Delete thought
     */
    authenticate("jwt") {
        delete(RouteUtils.THOUGHT) {
            val id: String? = call.parameters["id"]

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Id parameter must not be empty")
                return@delete
            }

            val removedThought: Boolean = removeThought.invoke(id)

            if (removedThought) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound, "Thought was not found")
        }
    }
}