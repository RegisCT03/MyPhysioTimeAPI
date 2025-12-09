package com.example.application.cases

import com.example.application.dtos.request.UserRegisterRequestDto
import com.example.application.dtos.response.UserResponseDto
import com.example.domain.interfaces.repositories.IUserRepository
import com.example.domain.interfaces.services.IRoleService
import com.example.domain.interfaces.services.IUserService
import com.example.domain.models.ClientStats
import com.example.domain.models.Role
import com.example.domain.models.User
import com.example.infrastructure.repositories.UserRepository

class UserCase(
    private val userService: IUserService,
    private val roleService: IRoleService
) {
    val errors: MutableList<String> = mutableListOf()
    suspend fun createUser(requestDto: UserRegisterRequestDto) : UserResponseDto {
        if (userService.getUserByEmail(requestDto.email) != null) {
            errors.add("Error, user already exists in bd")
            error("Error, user already exists in bd")
        }
        val createdUser = userService.createUser(requestDto)
        val role = roleService.getRoleById(createdUser.roleId) ?: error("Role not found")
        return mapToUserResponse(createdUser, role)
    }

    suspend fun findUserByEmail(email: String): UserResponseDto {
        val user = userService.getUserByEmail(email)
        user?.let {
            val role = roleService.getRoleById(it.roleId) ?: error("Role not found")
            return mapToUserResponse(it, role)
        } ?: run {
            error("User not found")
        }
    }

    suspend fun getAllClients():  Result<List<User>> {
        return try {
            val clients = userService.findAllClients()
            Result.success(clients)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun mapToUserResponse(user: User, role: Role): UserResponseDto = UserResponseDto(
        id = user.id ?: 0,
        stripeId = user.stripeId,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        phone = user.phone,
        createdAt = user.createdAt.toString(),
        updatedAt = user.updatedAt.toString(),
        token = ""
    )
}