package com.example.application.cases

import com.example.application.dtos.response.BookingResponseDto
import com.example.domain.interfaces.services.IBookingService
import com.example.domain.models.Booking

class BookingCase(
    private val bookingService: IBookingService
) {
    suspend fun getTodayPending(): List<BookingResponseDto> =
        bookingService.getTodayPending().map { mapToBookingResponse(it) }

    suspend fun getAll(): List<BookingResponseDto> =
        bookingService.getAll().map { mapToBookingResponse(it) }

    suspend fun getByStatus(status: String): List<BookingResponseDto> =
        bookingService.getByStatus(status).map { mapToBookingResponse(it) }

    suspend fun getById(id: Int): BookingResponseDto? =
        bookingService.getById(id)?.let { mapToBookingResponse(it) }

    suspend fun updateStatus(id: Int, status: String): BookingResponseDto? =
        bookingService.updateStatus(id, status)?.let { mapToBookingResponse(it) }

    private fun mapToBookingResponse(b: Booking) = BookingResponseDto(
        id = b.id ?: 0,
        serviceId = b.serviceId,
        clientId = b.clientId,
        physiotherapeutId = b.physiotherapeutId,
        date = b.date.toString(),
        state = b.state,
        notes = b.notes,
        createdAt = b.createdAt.toString(),
        updatedAt = b.updatedAt.toString()
    )
}