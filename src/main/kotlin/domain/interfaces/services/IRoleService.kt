package com.example.domain.interfaces.services

import com.example.domain.models.Role

interface IRoleService {
    suspend fun getRoleById(id: Int): Role?
}