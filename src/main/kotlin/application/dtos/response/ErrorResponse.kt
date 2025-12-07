package com.example.application.dtos.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val success: Boolean = false,
    val message: String,
    val errors: List<String> = emptyList()
)
