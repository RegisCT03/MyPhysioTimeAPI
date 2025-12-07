package com.example.domain.interfaces.services

import com.example.application.dtos.request.ServiceRequestDto
import com.example.domain.models.Service

interface IServiceService {
    suspend fun createService(serviceRequestDto: ServiceRequestDto) : Service
    suspend fun getServices(): List<Service>
    suspend fun getServiceById(id: Int): Service?
    suspend fun updateService(id: Int, serviceRequestDto: ServiceRequestDto): Service?
    suspend fun deleteService(id: Int) : Service?
    suspend fun changeStatus(id: Int) : Service
}