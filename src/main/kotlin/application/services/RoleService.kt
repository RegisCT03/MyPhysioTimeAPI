package com.example.application.services

import com.example.domain.interfaces.repositories.IRoleRepository
import com.example.domain.interfaces.services.IRoleService
import com.example.domain.models.Role

class RoleService(
    private val roleRepository: IRoleRepository
) : IRoleService {
    override suspend fun getRoleById(id: Int): Role? = roleRepository.getById(id)
}