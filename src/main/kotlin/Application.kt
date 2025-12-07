package com.example

import com.example.infrastructure.database.DatabaseFactory
import com.example.presentation.plugins.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    configureRouting()
}
