package com.example.application.dtos.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val id: Int,
    val stripeId: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val createdAt: String,
    val updatedAt: String,
    val token: String
)
