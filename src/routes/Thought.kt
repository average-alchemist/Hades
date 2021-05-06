package io.aethibo.routes

import io.aethibo.entities.response.Thought
import io.aethibo.usecases.GetThoughtUseCase
import io.aethibo.utils.RouteUtils.THOUGHT
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.thought() {

    val getThought: GetThoughtUseCase by inject()

    /**
     * Get single thought
     */
    get(THOUGHT) {
        val id: String? = call.parameters["id"]

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Id parameter must not be empty")
            return@get
        }

        val thought: Thought? = getThought.invoke(id)

        if (thought != null) call.respond(thought)
        else call.respond(HttpStatusCode.NotFound, "Thought was not found")
    }
}