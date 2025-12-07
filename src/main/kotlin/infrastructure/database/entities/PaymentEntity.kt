package com.example.infrastructure.database.entities

import org.jetbrains.exposed.sql.Table

object PaymentEntity: Table("payments") {
    val id = integer("id").autoIncrement()
    val stripePaymentId = varchar("stripe_payment_id", 255).nullable()
    val serviceId = integer("service_id")
        .references(ServiceEntity.id)
    val clientId = integer("client_id")
        .references(UserEntity.id)

    override val primaryKey = PrimaryKey(id)

}