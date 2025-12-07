package com.example.presentation.routing.routes

import com.example.application.cases.UserCase
import com.example.application.dtos.request.UserLoginRequestDto
import com.example.application.dtos.request.UserRegisterRequestDto
import com.example.application.dtos.response.ErrorResponse
import com.example.application.services.JwtService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(
    userCase: UserCase,
    jwtService: JwtService
) {
    route("/register"){
        post {
            try {
                val request = call.receive<UserRegisterRequestDto>()
                val response = userCase.createUser(request)
                response.copy(token = jwtService.createJWTToken(UserLoginRequestDto(request.email, request.password)) ?: "")
                call.respond(HttpStatusCode.Created, response)
            }catch (e: Exception){
                call.application.environment.log.error("Error en registro", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = userCase.errors)
                )
            }
        }
    }
    route("/login"){
        post {
            try {
                val request = call.receive<UserLoginRequestDto>()
                val token = jwtService.createJWTToken(request)
                token?.let {
                    var user = userCase.findUserByEmail(request.email)
                    user = user.copy(token = it)
                    call.respond(HttpStatusCode.OK, user)
                } ?: error("Invalid credentials")
            }catch (e: Exception){
                call.application.environment.log.error("Error en login", e)
                call.respond(
                    HttpStatusCode.Unauthorized,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = mutableListOf("Crendenciales inválidas"))
                )
            }
        }
    }
}