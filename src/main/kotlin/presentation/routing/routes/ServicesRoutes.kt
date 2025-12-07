package com.example.presentation.routing.routes

import com.example.application.cases.ServiceCase
import com.example.application.dtos.request.ServiceRequestDto
import com.example.application.dtos.response.ErrorResponse
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serviceRoutes(serviceCase: ServiceCase){
    authenticate {
        post {
            try {
                val request = call.receive<ServiceRequestDto>()
                val createdService = serviceCase.createService(request)
                call.respond(HttpStatusCode.Created, createdService)
            }catch (e: Exception){
                call.application.environment.log.error("Error en registro", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
        get {
            try {
                val services = serviceCase.getServices()
                call.respond(HttpStatusCode.OK, services)
            }catch (e: Exception){
                call.application.environment.log.error("Error en la lectura", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
        get("/{id}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid ID")
                val service = serviceCase.getServiceById(id)
                call.respond(HttpStatusCode.OK, service)
            }catch (e: Exception){
                call.application.environment.log.error("Error en la lectura", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
        put("/{id}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid ID")
                val request = call.receive<ServiceRequestDto>()
                val updatedService = serviceCase.updateService(id, request)
                call.respond(HttpStatusCode.OK, updatedService)
            }catch (e: Exception){
                call.application.environment.log.error("Error en la lectura", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
        delete("/{id}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid ID")
                val deletedService = serviceCase.deleteService(id)
                call.respond(HttpStatusCode.OK, deletedService)
            }catch (e: Exception){
                call.application.environment.log.error("Error en la lectura", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
        patch("/{id}/change-status") {
            try {
                val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid ID")
                val changedService = serviceCase.changeStatus(id)
                call.respond(HttpStatusCode.OK, changedService)
            }catch (e: Exception){
                call.application.environment.log.error("Error en la lectura", e)
                call.respond(
                    HttpStatusCode.BadRequest,
                    ErrorResponse(message = e.message ?: "Datos invalidos", errors = listOf(e.message.toString()))
                )
            }
        }
    }
}