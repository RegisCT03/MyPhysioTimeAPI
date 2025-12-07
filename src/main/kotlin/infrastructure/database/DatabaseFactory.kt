package com.example.infrastructure.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    private lateinit var database: Database

    fun init(config: DatabaseConfig = DatabaseConfig()){
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = config.jdbc
            driverClassName = config.driver
            username = config.username
            password = config.password
            maximumPoolSize = config.maxPoolSize
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"

            validate()
        }

        val dataSource = HikariDataSource(hikariConfig)
        database = Database.connect(dataSource)

        println("Database initialized successfully")
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction (Dispatchers.IO) { block() }
}