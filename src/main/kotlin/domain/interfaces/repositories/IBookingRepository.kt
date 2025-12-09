package com.example.domain.interfaces.repositories

import com.example.domain.models.Booking
import java.time.LocalDateTime

interface IBookingRepository {
    suspend fun getTodayPending(): List<Booking>
    suspend fun getAll(): List<Booking>
    suspend fun getByStatus(status: String): List<Booking>
    suspend fun getById(id: Int): Booking?
    suspend fun updateStatus(id: Int, status: String): Booking?
    suspend fun getByClientId(clientId: Int): List<Booking>
}