package com.example.application.services

import com.example.domain.interfaces.repositories.IBookingRepository
import com.example.domain.interfaces.services.IBookingService
import com.example.domain.models.Booking

class BookingService(
    private val bookingRepository: IBookingRepository
): IBookingService {
    override suspend fun getTodayPending(): List<Booking> = bookingRepository.getTodayPending()
    override suspend fun getAll(): List<Booking> = bookingRepository.getAll()
    override suspend fun getByStatus(status: String): List<Booking> = bookingRepository.getByStatus(status)
    override suspend fun getById(id: Int): Booking? = bookingRepository.getById(id)
    override suspend fun updateStatus(id: Int, status: String): Booking? = bookingRepository.updateStatus(id, status)
    override suspend fun getByClientId(clientId: Int): List<Booking> = bookingRepository.getByClientId(clientId)
}