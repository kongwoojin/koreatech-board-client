import androidx.compose.runtime.Composable
import di.appModule
import di.networkModule
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication
import ui.theme.KoreatechBoardTheme


@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule(), networkModule())
    }) {
        PreComposeApp {
            KoreatechBoardTheme {
                NavigatorRoot()
            }
        }
    }
}