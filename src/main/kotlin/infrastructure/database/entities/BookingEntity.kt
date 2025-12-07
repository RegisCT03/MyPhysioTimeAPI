package com.example.infrastructure.database.entities

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object BookingEntity: Table("bookings") {
    val id = integer("id").autoIncrement()
    val serviceId = integer("service_id")
        .references(ServiceEntity.id)
    val clientId = integer("client_id")
        .references(UserEntity.id)
    val physiotherapeutId = integer("physiotherapeut_id")
        .references(UserEntity.id)
    val date = datetime("date")
    val state = varchar("state", 255)
    val notes = varchar("notes", 10000)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}