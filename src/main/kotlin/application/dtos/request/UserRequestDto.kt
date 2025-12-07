package com.example.application.dtos.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val password: String
)

@Serializable
data class UserLoginRequestDto(
    val email: String,
    val password: String
)
