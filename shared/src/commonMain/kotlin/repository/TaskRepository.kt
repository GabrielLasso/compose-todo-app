package repository

import entity.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object TaskRepository {
    private val fakeDatabase = MutableStateFlow<List<Task>>(emptyList())

    fun getTasks(): StateFlow<Collection<Task>> = fakeDatabase.asStateFlow()

    fun createTask(name: String, description: String) {
        val tasks = fakeDatabase.value.toMutableList()

        tasks.add(Task(name, description, false))

        fakeDatabase.value = tasks
    }

    fun deleteTask(task: Task) {
        val tasks = fakeDatabase.value.toMutableList()

        tasks.remove(task)

        fakeDatabase.value = tasks
    }

    fun toggleDone(task: Task) {
        val tasks = fakeDatabase.value.toMutableList()

        val index = tasks.indexOf(task)
        tasks[index] = tasks[index].copy(done = !tasks[index].done)

        fakeDatabase.value = tasks
    }
}