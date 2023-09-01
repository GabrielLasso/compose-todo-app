package viewmodel

import entity.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import repository.TaskRepository

class HomeViewModel : ViewModel() {
    val tasks = TaskRepository.tasks

    init {
        viewModelScope.launch {
            TaskRepository.updateTasks()
        }
    }

    fun onClickTask(task: Task) {
        viewModelScope.launch {
            TaskRepository.toggleDone(task)
        }
    }

    fun onClickDeleteTask(task: Task) {
        viewModelScope.launch {
            TaskRepository.deleteTask(task)
        }
    }
}
