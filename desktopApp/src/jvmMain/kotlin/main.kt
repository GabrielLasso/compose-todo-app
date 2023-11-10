import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    initKoinModule()
    application {
        Window(onCloseRequest = ::exitApplication) {
            MainView()
        }
    }
}
