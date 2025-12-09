package com.example.domain.models

import java.time.LocalDateTime

data class ClientStats(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String?,
    val totalBookings: Int,
    val lastVisit: LocalDateTime?,
    val preferredService: String?
)