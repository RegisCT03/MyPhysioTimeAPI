package com.example.infrastructure.database.entities

import org.jetbrains.exposed.sql.Table

object RoleEntity : Table("roles") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}