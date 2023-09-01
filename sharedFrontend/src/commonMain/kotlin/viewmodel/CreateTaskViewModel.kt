package viewmodel

import entity.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import repository.TaskRepository

class CreateTaskViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    fun changeName(newName: String) {
        _name.value = newName
    }

    fun changeDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun save() {
        viewModelScope.launch {
            TaskRepository.createTask(Task(name.value, description.value, false))
        }
    }
}
