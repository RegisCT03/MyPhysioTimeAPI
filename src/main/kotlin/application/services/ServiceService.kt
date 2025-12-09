package com.example.application.services

import com.example.application.dtos.request.ServiceRequestDto
import com.example.domain.interfaces.repositories.IServiceRepository
import com.example.domain.interfaces.services.IServiceService
import com.example.domain.models.Service

class ServiceService(
    private val serviceRepository: IServiceRepository
) : IServiceService {
    override suspend fun createService(serviceRequestDto: ServiceRequestDto): Service {
        val newService = Service(
            name = serviceRequestDto.name,
            price = serviceRequestDto.price,
            description = serviceRequestDto.description,
            expectedTime = serviceRequestDto.expectedTime,
            hasImage = serviceRequestDto.hasImage,
            stripeId = "",
            isActive = true
        )
        return serviceRepository.createService(newService)
    }

    override suspend fun getServices(): List<Service> {
        return serviceRepository.getServices()
    }

    override suspend fun getServiceById(id: Int): Service? {
        return serviceRepository.getServiceById(id)
    }

    override suspend fun updateService(id: Int, serviceRequestDto: ServiceRequestDto): Service? {
        val serviceToUpdate = Service(
            name = serviceRequestDto.name,
            price = serviceRequestDto.price,
            description = serviceRequestDto.description,
            expectedTime = serviceRequestDto.expectedTime,
            hasImage = serviceRequestDto.hasImage,
            stripeId = "",
            isActive = true
        )
        return serviceRepository.updateService(id, serviceToUpdate
        )
    }

    override suspend fun deleteService(id: Int): Service? {
        return serviceRepository.deleteService(id)
    }

    override suspend fun changeStatus(id: Int): Service {
        return serviceRepository.changeStatus(id)
    }

    override suspend fun getByStatus(status: String): List<Service> = serviceRepository.getByStatus(status)

    override suspend fun findById(id: Int): Service? {
        TODO("Not yet implemented")
    }
}