package io.aethibo

import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.Payload
import io.aethibo.framework.di.applicationModule
import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.routes.*
import io.aethibo.usecases.GetUserByIdUseCase
import io.aethibo.utils.JwtService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.SLF4JLogger

// fun main(args: Array<String>): Unit = EngineMain.main(args)

@KtorExperimentalLocationsAPI
fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        watchPaths = listOf("Hades"),
        module = Application::module,
        port = 8080
    ).start(wait = true)
}

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val jwtService: JwtService by inject()
    val getUser: GetUserByIdUseCase by inject()

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, repositoriesModule, useCasesModule)
    }

    install(Authentication) {
        jwt("jwt") {
            verifier(jwtService.verifier)
            realm = "thoughts app"

            validate {
                val payload: Payload = it.payload
                val claim: Claim = payload.getClaim("id")
                val claimString: String = claim.asString()
                val user = getUser.invoke(claimString)

                user
            }
        }
    }

    install(Routing) {
        loginRoute(jwtService)
        registerRoute()
        home()
        thoughts()
        thought()
        addThought()
        updateThought()
        deleteThought()
    }
}