package com.example.application.cases

import com.example.application.dtos.request.ServiceRequestDto
import com.example.application.dtos.response.ServiceResponseDto
import com.example.domain.interfaces.services.IServiceService
import com.example.domain.models.Service

class ServiceCase(
    private val serviceService: IServiceService
) {
    suspend fun createService(requestDto: ServiceRequestDto): ServiceResponseDto {
        val createdService = serviceService.createService(requestDto)
        return mapToServiceResponse(createdService)
    }

    suspend fun getServices(): List<ServiceResponseDto> {
        val services = serviceService.getServices()
        return services.map { mapToServiceResponse(it) }
    }

    suspend fun getServiceById(id: Int): ServiceResponseDto {
        val services = serviceService.getServiceById(id)
        return mapToServiceResponse(services ?: error("Service not found"))
    }

    suspend fun updateService(iid: Int, newServiceRequestDto: ServiceRequestDto): ServiceResponseDto {
        val updatedService = serviceService.updateService(iid, newServiceRequestDto)
        return mapToServiceResponse(updatedService ?: error("Service not found"))
    }

    suspend fun deleteService(id: Int): ServiceResponseDto {
        val deletedService = serviceService.deleteService(id)
        return mapToServiceResponse(deletedService ?: error("Service not found"))
    }

    suspend fun changeStatus(id: Int): ServiceResponseDto {
        val changedService = serviceService.changeStatus(id)
        return mapToServiceResponse(changedService)
    }

    private fun mapToServiceResponse(service:Service) = ServiceResponseDto(
        id = service.id ?: 0,
        name = service.name,
        price = service.price,
        description = service.description,
        expectedTime = service.expectedTime,
        hasImage = service.hasImage,
        stripeId = service.stripeId ?: "",
        isActive = service.isActive ?: true
    )
}