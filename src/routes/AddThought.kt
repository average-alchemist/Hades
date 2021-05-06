package io.aethibo.routes

import io.aethibo.entities.response.Thought
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.usecases.AddThoughtUseCase
import io.aethibo.utils.RouteUtils.THOUGHTS
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.addThought() {

    val addThought: AddThoughtUseCase by inject()

    /**
     * Add thought
     */
    post(THOUGHTS) {
        val receivedThought: ThoughtDraft = call.receive()
        val thought: Thought? = addThought.invoke(receivedThought)

        thought
            ?.let { call.respond(it) }
            ?: call.respond(HttpStatusCode.BadRequest, "Cannot post your thought. Please try again")
    }
}