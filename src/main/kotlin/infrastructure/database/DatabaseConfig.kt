package com.example.infrastructure.database

class DatabaseConfig(
    val jdbc: String = System.getenv("JDBC_DATABASE_URL") ?: run {
        val host = System.getenv("DB_HOST") ?: "localhost"
        val port = System.getenv("DB_PORT") ?: "5432"
        val database = System.getenv("DB_NAME") ?: error("DB_NAME is not set")
        "jdbc:postgresql://$host:$port/$database"
    },
    val username: String = System.getenv("DB_USER") ?: error("DB_USER is not set"),
    val password: String = System.getenv("DB_PASSWORD") ?: error("DB_PASSWORD is not set"),
    val driver: String = "org.postgresql.Driver",
    val maxPoolSize: Int = 10
)