package com.example.presentation.routing.routes

import com.example.application.cases.BookingCase
import com.example.application.dtos.response.ErrorResponse
import com.example.domain.models.Booking
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.bookingRoutes(bookingCase: BookingCase){
    authenticate {
        route("") {
            post {
                try {
                    val command = call.receive<Booking>()
                    val result = bookingCase.createBooking(command)

                    result.fold(
                        onSuccess = { booking ->
                            call.respond(HttpStatusCode.Created, booking)
                        },
                        onFailure = { e ->
                            call.application.environment.log.error("Error create booking", e)
                            call.respond(
                                HttpStatusCode.BadRequest,
                                ErrorResponse(
                                    message = e.message ?: "Error creating booking",
                                    errors = listOf(e.message.toString())
                                )
                            )
                        }
                    )
                } catch (e: Exception) {
                    call.application.environment.log.error("Unexpected error create booking", e)
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse(message = e.message ?: "Unexpected error", errors = listOf(e.message.toString()))
                    )
                }
            }

            get("/today-pending") {
                try {
                    val data = bookingCase.getTodayPending()
                    call.respond(HttpStatusCode.OK, data)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error getTodayPending", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            get {
                try {
                    val data = bookingCase.getAll()
                    call.respond(HttpStatusCode.OK, data)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error getAll bookings", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            get("/status/{status}") {
                try {
                    val status = call.parameters["status"] ?: error("Status required")
                    val data = bookingCase.getByStatus(status)
                    call.respond(HttpStatusCode.OK, data)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error getByStatus", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            get("/{id}") {
                try {
                    val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid id")
                    val data = bookingCase.getById(id) ?: error("Not found")
                    call.respond(HttpStatusCode.OK, data)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error get booking by id", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            put("/{id}/confirm") {
                try {
                    val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid id")
                    val updated = bookingCase.updateStatus(id, "CONFIRMED")
                    call.respond(HttpStatusCode.OK, updated!!)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error confirm booking", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            put("/{id}/complete") {
                try {
                    val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid id")
                    val updated = bookingCase.updateStatus(id, "COMPLETED")
                    call.respond(HttpStatusCode.OK, updated!!)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error complete booking", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }

            put("/{id}/cancel") {
                try {
                    val id = call.parameters["id"]?.toIntOrNull() ?: error("Invalid id")
                    val updated = bookingCase.updateStatus(id, "CANCELLED")
                    call.respond(HttpStatusCode.OK, updated!!)
                } catch (e: Exception) {
                    call.application.environment.log.error("Error cancel booking", e)
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = e.message ?: "Error", errors = listOf(e.message.toString())))
                }
            }
        }
    }
}