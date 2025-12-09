package com.example.domain.interfaces.services

import com.example.domain.models.Booking

interface IBookingService {
    suspend fun getTodayPending(): List<Booking>
    suspend fun getAll(): List<Booking>
    suspend fun getByStatus(status: String): List<Booking>
    suspend fun getById(id: Int): Booking?
    suspend fun updateStatus(id: Int, status: String): Booking?
    suspend fun getByClientId(clientId: Int): List<Booking>
    suspend fun create(command: Booking): Booking
}