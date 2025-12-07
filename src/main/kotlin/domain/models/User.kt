package com.example.domain.models

import java.time.LocalDateTime

data class User(
    val id: Int? = null,
    val roleId: Int,
    val stripeId: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
