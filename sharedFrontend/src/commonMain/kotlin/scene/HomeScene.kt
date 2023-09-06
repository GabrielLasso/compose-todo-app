package scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.koin.koinViewModel
import viewmodel.HomeViewModel

@Composable
fun HomeScene(
    onAdd: () -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val tasks by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Tasks")
        for (task in tasks) {
            Row {
                Checkbox(task.done, { viewModel.onClickTask(task) })
                Text(task.name)
                Button({ viewModel.onClickDeleteTask(task) }) {
                    Text("Delete")
                }
            }
        }
        Button(onAdd) {
            Text("+")
        }
    }
}
