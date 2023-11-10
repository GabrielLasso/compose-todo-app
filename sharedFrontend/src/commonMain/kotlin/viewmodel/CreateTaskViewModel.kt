package viewmodel

import androidx.compose.runtime.mutableStateOf
import entity.Task
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import repository.TaskRepository

class CreateTaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _name = mutableStateOf("")
    val name get() = _name.value
    private val _description = mutableStateOf("")
    val description get() = _description.value

    fun changeName(newName: String) {
        _name.value = newName
    }

    fun changeDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun save() {
        viewModelScope.launch {
            taskRepository.createTask(Task(name, description, false))
        }
    }
}
