package com.example.infrastructure.repositories

import com.example.domain.interfaces.repositories.IUserRepository
import com.example.domain.models.ClientStats
import com.example.domain.models.User
import com.example.infrastructure.database.DatabaseFactory
import com.example.infrastructure.database.entities.UserEntity
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime

class UserRepository : IUserRepository {
    override suspend fun createUser(user: User): User = DatabaseFactory.dbQuery {
        val userSaved = UserEntity.insert {
            it[roleId] = 2
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email
            it[phone] = user.phone
            it[password] = user.password
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        } get UserEntity.id
        user.copy(id = userSaved)
    }

    override suspend fun getUserByEmail(email: String): User? = DatabaseFactory.dbQuery {
        UserEntity.select { UserEntity.email eq email }
            .map { resultRowToUser(it) }
            .singleOrNull()
    }

    override suspend fun getUserById(id: Int): User? = DatabaseFactory.dbQuery {
        UserEntity.select { UserEntity.id eq id }
            .map { resultRowToUser(it) }
            .singleOrNull()
    }

    override suspend fun getAllClients(): List<User> = DatabaseFactory.dbQuery {
        UserEntity.selectAll().map {
            resultRowToUser(it)
        }
    }

    private fun resultRowToUser(row: ResultRow)  = User(
        id = row[UserEntity.id],
        roleId = row[UserEntity.roleId],
        stripeId = row[UserEntity.stripeId],
        firstName = row[UserEntity.firstName],
        lastName = row[UserEntity.lastName],
        email = row[UserEntity.email],
        phone = row[UserEntity.phone],
        password = row[UserEntity.password],
        createdAt = row[UserEntity.createdAt],
        updatedAt = row[UserEntity.updatedAt]
    )
}