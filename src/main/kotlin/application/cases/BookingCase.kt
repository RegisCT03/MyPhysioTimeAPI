package com.example.application.cases

import com.example.application.dtos.response.BookingResponseDto
import com.example.domain.interfaces.services.IBookingService
import com.example.domain.models.Booking
import com.example.domain.interfaces.repositories.IBookingRepository
import com.example.domain.interfaces.repositories.IServiceRepository
import com.example.domain.interfaces.repositories.IUserRepository

class BookingCase(
    private val bookingService: IBookingService,
    private val serviceRepository: IServiceRepository,
    private val userRepository: IUserRepository
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

    suspend fun createBooking(command: Booking): Result<Booking> {
        serviceRepository.getServiceById(command.serviceId)
            ?: return Result.failure(Exception("Service not found"))

        userRepository.getUserById(command.clientId)
            ?: return Result.failure(Exception("Client not found"))

        return try {
            val booking = bookingService.create(command)
            Result.success(booking)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



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