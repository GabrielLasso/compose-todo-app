package scene

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import moe.tlaster.precompose.koin.koinViewModel
import viewmodel.CreateTaskViewModel

@Composable
fun CreateTaskScene(
    onBack: () -> Unit,
) {
    val viewModel = koinViewModel(CreateTaskViewModel::class)
    val name = viewModel.name
    val description = viewModel.description

    Column {
        Text("Nova tarefa")
        TextField(
            name,
            onValueChange = { viewModel.changeName(it) },
            placeholder = {
                Text("Nome")
            },
        )
        TextField(
            description,
            onValueChange = { viewModel.changeDescription(it) },
            placeholder = {
                Text("Descrição")
            },
        )
        Button(onClick = {
            viewModel.save()
            onBack()
        }) {
            Text("Salvar")
        }
    }
}
