package com.example.infrastructure.database.entities

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import kotlin.time.Instant

object UserEntity : Table("users") {
    val id = integer("id").autoIncrement()
    val roleId = integer("role_id")
        .uniqueIndex()
        .references(RoleEntity.id)
    val stripe_id = varchar("stripe_id", 255)
        .nullable()
    val first_name = varchar("first_name", 255)
    val last_name = varchar("last_name", 255)
    val email = varchar("email", 255)
    val phone = varchar("phone", 10)
    val password = varchar("password", 255)
    val created_at = datetime("created_at")
        .default(LocalDateTime.now())
    val updated_at = datetime("updated_at")
        .default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}