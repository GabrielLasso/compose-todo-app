import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import scene.CreateTaskScene
import scene.HomeScene

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            PreComposeApp {
                val navigator = rememberNavigator()
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
    }
}
