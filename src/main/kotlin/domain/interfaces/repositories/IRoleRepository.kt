package com.example.domain.interfaces.repositories

import com.example.domain.models.Role

interface IRoleRepository {
    suspend fun getById(id: Int) : Role?
}