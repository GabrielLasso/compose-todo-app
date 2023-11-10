package gateway

import entity.Task
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json

class TaskGateway {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getTasks(): Flow<List<Task>> = channelFlow {
        val response = client.get("http://10.0.2.2:8080/tasks").bodyAsText()

        val tasks = Json.decodeFromString<List<Task>>(response)

        send(tasks)
    }

    suspend fun createTask(task: Task) {
        client.post("http://10.0.2.2:8080/tasks") {
            header("Content-Type", "application/json")
            setBody(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        client.delete("http://10.0.2.2:8080/tasks") {
            header("Content-Type", "application/json")
            setBody(task)
        }
    }

    suspend fun toggleDone(task: Task) {
        client.post("http://10.0.2.2:8080/tasks/toggle") {
            header("Content-Type", "application/json")
            setBody(task)
        }
    }
}
