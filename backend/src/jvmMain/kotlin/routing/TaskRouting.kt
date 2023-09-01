package com.example.routing

import entity.Task
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

val fakeDatabase = mutableListOf(Task("Nome", "Descrição", false))
fun Routing.taskRouting() {
    get("/tasks") {
        call.respond(fakeDatabase)
    }

    post("/tasks") {
        val body = call.receiveText()
        val task = Json.decodeFromString<Task>(body)
        fakeDatabase.add(task)
        call.respond(mapOf("status" to "success"))
    }

    delete("/tasks") {
        val body = call.receiveText()
        val task = Json.decodeFromString<Task>(body)
        fakeDatabase.remove(task)
        call.respond(mapOf("status" to "success"))
    }

    post("/tasks/toggle") {
        val body = call.receiveText()
        val task = Json.decodeFromString<Task>(body)

        val index = fakeDatabase.indexOf(task)
        fakeDatabase[index] = fakeDatabase[index].copy(done = !fakeDatabase[index].done)

        call.respond(mapOf("status" to "success"))
    }
}
