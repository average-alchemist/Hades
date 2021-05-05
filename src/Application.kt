package io.aethibo

import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.routes.loginRoute
import io.aethibo.routes.registerRoute
import io.aethibo.routes.thoughtsRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(Routing) {
        loginRoute()
        registerRoute()
        thoughtsRoutes()
    }

    install(Koin) {
        SLF4JLogger()
        modules(repositoriesModule, useCasesModule)
    }
}

