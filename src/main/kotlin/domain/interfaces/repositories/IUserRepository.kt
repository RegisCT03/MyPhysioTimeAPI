package com.example.domain.interfaces.repositories

import com.example.domain.models.User

interface IUserRepository {
    suspend fun createUser(
        user: User
    ): User

    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserById(id: Int) : User?
}