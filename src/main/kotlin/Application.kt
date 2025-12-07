package com.example

import com.example.application.services.JwtService
import com.example.infrastructure.database.DatabaseFactory
import com.example.presentation.di.DependenciesDeclaration
import com.example.presentation.plugins.configSecurity
import com.example.presentation.plugins.configureSerialization
import com.example.presentation.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        anyHost()

        allowCredentials = true
    }
    val jwtService = JwtService(this, DependenciesDeclaration.userService)
    configureSerialization()
    configSecurity(jwtService)
    configureRouting (jwtService)
}
