package com.example.application.services

import com.example.application.dtos.request.UserRegisterRequestDto
import com.example.domain.interfaces.repositories.IUserRepository
import com.example.domain.interfaces.services.IPasswordService
import com.example.domain.interfaces.services.IUserService
import com.example.domain.models.User
import java.time.LocalDateTime

class UserService(
    override val passwordService: IPasswordService,
    private val userRepository: IUserRepository
): IUserService {
    override suspend fun createUser(user: UserRegisterRequestDto): User {
        val hashedPassword = passwordService.hashPassword(password = user.password)
        val newUser = User(
            roleId = 2,
            stripeId = null,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phone = user.phone,
            password = hashedPassword,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
        val savedUser = userRepository.createUser(newUser)
        return savedUser
    }

    override suspend fun getUserByEmail(email: String): User? = userRepository.getUserByEmail(email)

    override suspend fun getUserById(id: Int): User? = userRepository.getUserById(id)
}