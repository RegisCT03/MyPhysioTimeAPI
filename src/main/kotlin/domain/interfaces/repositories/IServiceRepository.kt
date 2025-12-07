package com.example.domain.interfaces.repositories

import com.example.domain.models.Service

interface IServiceRepository {
    suspend fun createService(service: Service): Service
    suspend fun getServices(): List<Service>
    suspend fun getServiceById(id: Int): Service?
    suspend fun updateService(id: Int, service: Service): Service?
    suspend fun deleteService(id: Int): Service?
    suspend fun changeStatus(id: Int): Service
}