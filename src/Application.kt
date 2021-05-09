package io.aethibo

import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.Payload
import io.aethibo.framework.di.applicationModule
import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.repositories.MainRepository
import io.aethibo.routes.*
import io.aethibo.utils.JwtService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val jwtService: JwtService by inject()
    val repository: MainRepository by inject()

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, repositoriesModule, useCasesModule)
    }

    install(Authentication) {
        jwt("auth") {
            verifier(jwtService.verifier)
            realm = "thoughts app"

            validate {
                val payload: Payload = it.payload
                val claim: Claim = payload.getClaim("id")
                val claimString: String = claim.asString()
                val user = repository.getUserById(claimString)

                user
            }
        }
    }

    install(Routing) {
        loginRoute(repository, jwtService)
        registerRoute()
        home()
        thoughts()
        thought()
        addThought()
        updateThought()
        deleteThought()
    }
}