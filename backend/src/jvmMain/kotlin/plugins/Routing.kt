package com.example.plugins

import com.example.routing.taskRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing


fun Application.configureRouting() {
    routing {
        taskRouting()
    }
}