package com.example.domain.models

data class Service(
    val id: Int? = null,
    val name: String,
    val description: String,
    val price: Float,
    val expectedTime: Int,
    val hasImage: Boolean,
    val stripeId: String?,
    val isActive: Boolean?
)