package com.example.infrastructure.repositories

import com.example.domain.interfaces.repositories.IRoleRepository
import com.example.domain.models.Role
import com.example.infrastructure.database.DatabaseFactory
import com.example.infrastructure.database.entities.RoleEntity
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class RoleRepository: IRoleRepository {
    override suspend fun getById(id: Int): Role? = DatabaseFactory.dbQuery {
        RoleEntity.select {
            RoleEntity.id eq id
        }
            .map { resultRowToRole(it) }
            .singleOrNull()
    }

    private fun resultRowToRole(row: ResultRow): Role = Role(
        id = row[RoleEntity.id],
        name = row[RoleEntity.name]
    )
}