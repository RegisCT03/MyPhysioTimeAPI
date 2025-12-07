package com.example.presentation.routing

import com.example.application.services.JwtService
import com.example.presentation.di.DependenciesDeclaration
import com.example.presentation.routing.routes.authRoutes
import com.example.presentation.routing.routes.serviceRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(jwtService: JwtService) {
    routing {
        route("api/auth") {
            authRoutes(DependenciesDeclaration.userCase, jwtService)
        }
        route("api/service"){
            serviceRoutes(DependenciesDeclaration.serviceCase)
        }
    }
}
