package repository

import entity.Task
import gateway.TaskGateway
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

object TaskRepository {
    private val _tasks = MutableStateFlow(emptyList<Task>())

    val tasks = _tasks.asStateFlow()

    suspend fun updateTasks() {
        _tasks.emit(TaskGateway.getTasks().first())
    }

    suspend fun createTask(task: Task) {
        TaskGateway.createTask(task)
        _tasks.value += task
    }

    suspend fun toggleDone(task: Task) {
        TaskGateway.toggleDone(task)
        val tasks = _tasks.value.toMutableList()
        val index = tasks.indexOf(task)
        tasks[index] = task.copy(done = !task.done)
        _tasks.value = tasks
    }

    suspend fun deleteTask(task: Task) {
        TaskGateway.deleteTask(task)
        val tasks = _tasks.value.toMutableList()
        val index = tasks.indexOf(task)
        tasks.removeAt(index)
        _tasks.value = tasks
    }
}
