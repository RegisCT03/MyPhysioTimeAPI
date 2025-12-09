package com.example.application.services

import com.example.domain.interfaces.repositories.IBookingRepository
import com.example.domain.interfaces.services.IBookingService
import com.example.domain.models.Booking
import com.example.infrastructure.database.DatabaseFactory
import com.example.infrastructure.database.entities.BookingEntity
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime

class BookingService(
    private val bookingRepository: IBookingRepository
): IBookingService {
    override suspend fun getTodayPending(): List<Booking> = bookingRepository.getTodayPending()
    override suspend fun getAll(): List<Booking> = bookingRepository.getAll()
    override suspend fun getByStatus(status: String): List<Booking> = bookingRepository.getByStatus(status)
    override suspend fun getById(id: Int): Booking? = bookingRepository.getById(id)
    override suspend fun updateStatus(id: Int, status: String): Booking? = bookingRepository.updateStatus(id, status)
    override suspend fun getByClientId(clientId: Int): List<Booking> = bookingRepository.getByClientId(clientId)
    override suspend fun create(command: Booking): Booking = DatabaseFactory.dbQuery {
        val now = LocalDateTime.now()
        val id = BookingEntity.insert {
            it[serviceId] = command.serviceId
            it[clientId] = command.clientId
            it[date] = command.date
            it[state] = command.state
            it[notes] = command.notes
            it[createdAt] = now
            it[updatedAt] = now
        } get BookingEntity.id

        command.copy(id = id)
        command
    }


}