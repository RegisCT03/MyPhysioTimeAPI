package com.example.presentation.di

import com.example.application.cases.BookingCase
import com.example.application.cases.ServiceCase
import com.example.application.cases.UserCase
import com.example.application.services.BookingService
import com.example.application.services.PasswordService
import com.example.application.services.RoleService
import com.example.application.services.ServiceService
import com.example.application.services.UserService
import com.example.domain.interfaces.repositories.IBookingRepository
import com.example.domain.interfaces.repositories.IRoleRepository
import com.example.domain.interfaces.repositories.IServiceRepository
import com.example.domain.interfaces.repositories.IUserRepository
import com.example.domain.interfaces.services.IBookingService
import com.example.domain.interfaces.services.IPasswordService
import com.example.domain.interfaces.services.IRoleService
import com.example.domain.interfaces.services.IServiceService
import com.example.domain.interfaces.services.IUserService
import com.example.infrastructure.repositories.BookingRepository
import com.example.infrastructure.repositories.RoleRepository
import com.example.infrastructure.repositories.ServiceRepository
import com.example.infrastructure.repositories.UserRepository

class DependenciesDeclaration {

    companion object {
        val userRepository: IUserRepository = UserRepository()
        val passwordService: IPasswordService = PasswordService()
        val userService: IUserService = UserService(passwordService, userRepository)
        val roleRepository: IRoleRepository = RoleRepository()
        val roleService: IRoleService = RoleService(roleRepository)
        val userCase = UserCase(userService, roleService)

        val serviceRepository : IServiceRepository = ServiceRepository()
        val serviceService: IServiceService = ServiceService(serviceRepository)
        val serviceCase: ServiceCase = ServiceCase(serviceService)

        val bookingRepository: IBookingRepository = BookingRepository()
        val bookingService: IBookingService = BookingService(bookingRepository)
        val bookingCase: BookingCase = BookingCase(bookingService)
    }
}