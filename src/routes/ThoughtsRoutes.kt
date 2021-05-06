package io.aethibo.routes

import io.aethibo.entities.response.Thought
import io.aethibo.usecases.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.thoughtsRoutes() {

    val getThoughts: GetThoughtsUseCase by inject()
    val getThought: GetThoughtUseCase by inject()
    val addThought: AddThoughtUseCase by inject()
    val updateThought: UpdateThoughtUseCase by inject()
    val removeThought: RemoveThoughtUseCase by inject()

    route("/") {
        get {
            call.respondText("Hello World!")
        }
    }

    /**
     * Get all thoughts
     */
    route("/thoughts") {
        get {
            call.respond(getThoughts.invoke())
        }
    }

    /**
     * Get single thought
     */
    route("/thoughts/{id}") {
        get {
            val id: Int? = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Id parameter must not be empty")
                return@get
            }

            val thought: Thought? = getThought.invoke(id)

            if (thought != null) call.respond(thought)
            else call.respond(HttpStatusCode.NotFound, "Thought was not found")
        }
    }

    /**
     * Add thought
     */
    route("/thoughts") {
        post {
            val receivedThought: Thought = call.receive()
            val thought: Thought? = addThought.invoke(receivedThought)

            call.respond(thought!!)
        }
    }

    /**
     * Edit thought
     */
    route("/thoughts/{id}") {
        put {
            val id: Int? = call.parameters["id"]?.toIntOrNull()
            val receivedThought: Thought = call.receive()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Id parameter must not be empty")
                return@put
            }

            val updatedThought: Boolean = updateThought.invoke(id, receivedThought)

            if (updatedThought) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound, "Thought was not found")
        }
    }

    /**
     * Delete thought
     */
    route("/thoughts/{id}") {
        delete {
            val id: Int? = call.parameters["id"]?.toIntOrNull()

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