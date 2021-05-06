package io.aethibo.routes

import io.aethibo.entities.response.Thought
import io.aethibo.entities.response.ThoughtDraft
import io.aethibo.usecases.*
import io.aethibo.utils.RouteUtils.HomeRoute
import io.aethibo.utils.RouteUtils.THOUGHT
import io.aethibo.utils.RouteUtils.THOUGHTS
import io.aethibo.utils.RouteUtils.ThoughtsRoute
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Route.thoughtsRoutes() {

    val getThoughts: GetThoughtsUseCase by inject()
    val getThought: GetThoughtUseCase by inject()
    val addThought: AddThoughtUseCase by inject()
    val updateThought: UpdateThoughtUseCase by inject()
    val removeThought: RemoveThoughtUseCase by inject()

    get<HomeRoute> {
        call.respondText("Hello World!")
    }

    /**
     * Get all thoughts
     */
    get<ThoughtsRoute> {
        call.respond(getThoughts.invoke())
    }

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

    /**
     * Edit thought
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

    /**
     * Delete thought
     */
    delete(THOUGHT) {
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