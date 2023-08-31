package viewmodel

import entity.Task
import moe.tlaster.precompose.viewmodel.ViewModel
import repository.TaskRepository

class HomeViewModel: ViewModel() {
    val tasks = TaskRepository.getTasks()

    fun onClickTask(task: Task) {
        TaskRepository.toggleDone(task)
    }

    fun onClickDeleteTask(task: Task) {
        TaskRepository.deleteTask(task)
    }
}