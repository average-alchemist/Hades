package io.aethibo.utils

import io.ktor.locations.*

object RouteUtils {

    private const val API_ENDPOINT = "/api/v1"

    const val HOME = "$API_ENDPOINT/"
    const val THOUGHTS = "$API_ENDPOINT/thoughts"
    const val THOUGHT = "$API_ENDPOINT/thoughts/{id}"
    const val LOGIN = "/login"
    const val REGISTER = "/register"

    // Type-safe routes
    @KtorExperimentalLocationsAPI
    @Location(HOME)
    class HomeRoute

    @KtorExperimentalLocationsAPI
    @Location(THOUGHTS)
    class ThoughtsRoute

    @KtorExperimentalLocationsAPI
    @Location(THOUGHT)
    class ThoughtRoute

    @KtorExperimentalLocationsAPI
    @Location(LOGIN)
    class Login

    @KtorExperimentalLocationsAPI
    @Location(REGISTER)
    class Register
}