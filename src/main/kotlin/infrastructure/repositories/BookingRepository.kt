package com.example.infrastructure.repositories


import com.example.domain.interfaces.repositories.IBookingRepository
import com.example.domain.models.Booking
import com.example.infrastructure.database.DatabaseFactory
import com.example.infrastructure.database.entities.BookingEntity
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime
import java.time.LocalTime

class BookingRepository: IBookingRepository {
    override suspend fun getTodayPending(): List<Booking> = DatabaseFactory.dbQuery {
        val now = LocalDateTime.now()
        val start = now.toLocalDate().atStartOfDay()
        val end = now.toLocalDate().atTime(LocalTime.MAX)

        BookingEntity.select {
            (BookingEntity.date.between(start, end)) and (BookingEntity.state eq "PENDING")
        }.map { resultRowToBooking(it) }
    }

    override suspend fun getAll(): List<Booking> = DatabaseFactory.dbQuery {
        BookingEntity.selectAll().map { resultRowToBooking(it) }
    }

    override suspend fun getByStatus(status: String): List<Booking> = DatabaseFactory.dbQuery {
        BookingEntity.select { BookingEntity.state eq status }.map { resultRowToBooking(it) }
    }

    override suspend fun getById(id: Int): Booking? = DatabaseFactory.dbQuery {
        BookingEntity.select { BookingEntity.id eq id }.mapNotNull { resultRowToBooking(it) }.singleOrNull()
    }

    override suspend fun updateStatus(id: Int, status: String): Booking? = DatabaseFactory.dbQuery {
        BookingEntity.update({ BookingEntity.id eq id }) {
            it[BookingEntity.state] = status
            it[BookingEntity.updatedAt] = LocalDateTime.now()
        }
        getById(id)
    }

    override suspend fun getByClientId(clientId: Int): List<Booking> = DatabaseFactory.dbQuery {
        BookingEntity.select { BookingEntity.clientId eq clientId }
            .orderBy(BookingEntity.date, SortOrder.DESC)
            .map { resultRowToBooking(it) }
    }

    private fun resultRowToBooking(row: ResultRow) = Booking(
        id = row[BookingEntity.id],
        serviceId = row[BookingEntity.serviceId],
        clientId = row[BookingEntity.clientId],
        physiotherapeutId = row[BookingEntity.physiotherapeutId],
        date = row[BookingEntity.date],
        state = row[BookingEntity.state],
        notes = row[BookingEntity.notes],
        createdAt = row[BookingEntity.createdAt],
        updatedAt = row[BookingEntity.updatedAt]
    )
}