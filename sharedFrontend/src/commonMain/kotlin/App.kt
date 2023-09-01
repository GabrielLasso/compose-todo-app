import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import scene.CreateTaskScene
import scene.HomeScene

@Composable
fun App() {
    val navigator = rememberNavigator()
    MaterialTheme {
        NavHost(
            navigator = navigator,
            initialRoute = "/home",
        ) {
            scene("/home") {
                HomeScene(
                    onAdd = { navigator.navigate("/add") },
                )
            }
            scene("/add") {
                CreateTaskScene(
                    onBack = { navigator.goBack() },
                )
            }
        }
    }
}
