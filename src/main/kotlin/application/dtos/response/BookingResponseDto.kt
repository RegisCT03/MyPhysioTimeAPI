package com.example.application.dtos.response

data class BookingResponseDto(
    val id: Int,
    val serviceId: Int,
    val clientId: Int,
    val physiotherapeutId: Int,
    val date: String,
    val state: String,
    val notes: String,
    val createdAt: String,
    val updatedAt: String
)