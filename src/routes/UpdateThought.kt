package io.aethibo.routes

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.usecases.UpdateThoughtUseCase
import io.aethibo.utils.RouteUtils.THOUGHT
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.updateThought() {

    val updateThought: UpdateThoughtUseCase by inject()

    /**
     * Update/Edit thought
     */
    put(THOUGHT) {
        val id: String? = call.parameters["id"]
        val receivedThought: ThoughtDraft = call.receive()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Id parameter must not be empty")
            return@put
        }

        val updatedThought: Boolean = updateThought.invoke(id, receivedThought)

        if (updatedThought) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound, "Thought was not found")
    }
}