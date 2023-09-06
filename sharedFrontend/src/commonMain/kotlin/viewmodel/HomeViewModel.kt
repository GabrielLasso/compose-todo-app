package viewmodel

import entity.Task
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import repository.TaskRepository

class HomeViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    val tasks = taskRepository.tasks

    init {
        viewModelScope.launch {
            taskRepository.updateTasks()
        }
    }

    fun onClickTask(task: Task) {
        viewModelScope.launch {
            taskRepository.toggleDone(task)
        }
    }

    fun onClickDeleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}
