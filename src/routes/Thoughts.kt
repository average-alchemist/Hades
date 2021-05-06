package io.aethibo.routes

import io.aethibo.usecases.GetThoughtsUseCase
import io.aethibo.utils.RouteUtils.ThoughtsRoute
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Route.thoughts() {

    val getThoughts: GetThoughtsUseCase by inject()

    /**
     * Get all thoughts
     */
    get<ThoughtsRoute> {
        call.respond(getThoughts.invoke())
    }
}