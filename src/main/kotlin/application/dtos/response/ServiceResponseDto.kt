package com.example.application.dtos.response

import kotlinx.serialization.Serializable

@Serializable
data class ServiceResponseDto(
    val id: Int,
    val name: String,
    val price: Float,
    val description: String,
    val expectedTime: Int,
    val hasImage: Boolean,
    val stripeId: String,
    val isActive: Boolean
)