package com.example.infrastructure.repositories

import com.example.domain.interfaces.repositories.IServiceRepository
import com.example.domain.models.Service
import com.example.infrastructure.database.DatabaseFactory
import com.example.infrastructure.database.entities.ServiceEntity
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ServiceRepository: IServiceRepository {
    override suspend fun createService(service: Service): Service = DatabaseFactory.dbQuery {
        val serviceSaved = ServiceEntity.insert {
            it[name] = service.name
            it[price] = service.price
            it[description] = service.description
            it[expectedTime] = service.expectedTime
            it[hasImage] = service.hasImage
            it[stripeId] = service.stripeId
            it[isActive] = service.isActive!!
        } get ServiceEntity.id
        service.copy(id = serviceSaved)
    }

    override suspend fun getServices(): List<Service> = DatabaseFactory.dbQuery {
        ServiceEntity.selectAll().map { resultRowToService(it) }
    }

    override suspend fun getServiceById(id: Int): Service?  = DatabaseFactory.dbQuery {
        ServiceEntity.select { ServiceEntity.id eq id }
            .map { resultRowToService(it) }
            .singleOrNull()
    }

    override suspend fun updateService(id: Int, service: Service): Service? = DatabaseFactory.dbQuery {
        val bdService = getServiceById(id) ?: return@dbQuery null
        ServiceEntity.update({ ServiceEntity.id eq bdService.id!! }){
            it[name] = service.name
            it[price] = service.price
            it[description] = service.description
            it[expectedTime] = service.expectedTime
            it[hasImage] = service.hasImage
        }
        service
    }

    override suspend fun deleteService(id: Int): Service? = DatabaseFactory.dbQuery {
        val bdService = getServiceById(id) ?: return@dbQuery null
        ServiceEntity.deleteWhere { ServiceEntity.id eq bdService.id!! }
        bdService
    }

    override suspend fun changeStatus(id: Int): Service {
        val bdService = getServiceById(id) ?: throw Exception("Servicio no encontrado")
        ServiceEntity.update({ ServiceEntity.id eq bdService.id!! }){
            it[isActive] = !bdService.isActive!!
            }
        return bdService
    }

    private fun resultRowToService(row: ResultRow): Service {
        return Service(
            id = row[ServiceEntity.id],
            name = row[ServiceEntity.name],
            price = row[ServiceEntity.price],
            description = row[ServiceEntity.description] ?: "",
            expectedTime = row[ServiceEntity.expectedTime],
            hasImage = row[ServiceEntity.hasImage],
            stripeId = row[ServiceEntity.stripeId] ?: "",
            isActive = row[ServiceEntity.isActive]
        )
    }
}