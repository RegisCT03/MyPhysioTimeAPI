package com.example.domain.interfaces.services

import com.example.application.dtos.request.UserRegisterRequestDto
import com.example.domain.models.ClientStats
import com.example.domain.models.User

interface IUserService {
    val passwordService: IPasswordService
    suspend fun createUser(user: UserRegisterRequestDto): User
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: Int) : User?
    suspend fun findAllClients(): List<User>
}