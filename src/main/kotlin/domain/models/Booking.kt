package com.example.domain.models

import java.time.LocalDateTime

data class Booking(
    val id : Int? = null,
    val serviceId: Int,
    val clientId: Int,
    val physiotherapeutId: Int,
    val date: LocalDateTime,
    val state: String,
    val notes: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
    )