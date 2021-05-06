package io.aethibo.routes

import io.aethibo.utils.RouteUtils.HomeRoute
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Route.home() {


    get<HomeRoute> {
        call.respondText("Hello World!")
    }
}