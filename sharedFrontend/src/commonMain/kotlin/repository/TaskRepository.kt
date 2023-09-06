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

class TaskRepository(
    private val gateway: TaskGateway
) {
    private val _tasks = MutableStateFlow(emptyList<Task>())

    val tasks = _tasks.asStateFlow()

    suspend fun updateTasks() {
        _tasks.emit(gateway.getTasks().first())
    }

    suspend fun createTask(task: Task) {
        gateway.createTask(task)
        _tasks.value += task
    }

    suspend fun toggleDone(task: Task) {
        gateway.toggleDone(task)
        val tasks = _tasks.value.toMutableList()
        val index = tasks.indexOf(task)
        tasks[index] = task.copy(done = !task.done)
        _tasks.value = tasks
    }

    suspend fun deleteTask(task: Task) {
        gateway.deleteTask(task)
        val tasks = _tasks.value.toMutableList()
        val index = tasks.indexOf(task)
        tasks.removeAt(index)
        _tasks.value = tasks
    }
}
