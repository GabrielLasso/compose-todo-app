import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeWindow

fun main() {
    initKoinModule()
    application {
        PreComposeWindow(onCloseRequest = ::exitApplication) {
            MainView()
        }
    }
}
