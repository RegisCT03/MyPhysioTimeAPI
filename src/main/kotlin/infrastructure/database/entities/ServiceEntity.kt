package com.example.infrastructure.database.entities

import org.jetbrains.exposed.sql.Table

object ServiceEntity: Table("services") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val price = float("price")
    val description = varchar("description", 10000).nullable()
    val expectedTime = integer("expected_time")
    val hasImage = bool("has_image").default(false)
    val stripeId = varchar("stripe_id", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}