package com.example.application.dtos.request

import kotlinx.serialization.Serializable

@Serializable
data class ServiceRequestDto(
    val name: String,
    val price: Float,
    val description: String,
    val expectedTime: Int,
    val hasImage: Boolean
)