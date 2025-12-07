package com.example.presentation.di

import com.example.application.cases.UserCase
import com.example.application.services.PasswordService
import com.example.application.services.RoleService
import com.example.application.services.UserService
import com.example.domain.interfaces.repositories.IRoleRepository
import com.example.domain.interfaces.repositories.IUserRepository
import com.example.domain.interfaces.services.IPasswordService
import com.example.domain.interfaces.services.IRoleService
import com.example.domain.interfaces.services.IUserService
import com.example.infrastructure.repositories.RoleRepository
import com.example.infrastructure.repositories.UserRepository

class DependenciesDeclaration {

    companion object {
        val userRepository: IUserRepository = UserRepository()
        val passwordService: IPasswordService = PasswordService()
        val userService: IUserService = UserService(passwordService, userRepository)
        val roleRepository: IRoleRepository = RoleRepository()
        val roleService: IRoleService = RoleService(roleRepository)
        val userCase = UserCase(userService, roleService)
    }
}