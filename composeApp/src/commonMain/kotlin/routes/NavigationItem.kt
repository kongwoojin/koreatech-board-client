package routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import koreatechboard.composeapp.generated.resources.Res
import koreatechboard.composeapp.generated.resources.bottom_nav_bar_board
import koreatechboard.composeapp.generated.resources.bottom_nav_bar_home
import koreatechboard.composeapp.generated.resources.bottom_nav_bar_settings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed class NavigationItem(val route: String, val stringResource: StringResource, val icon: ImageVector) {
    data object Home : NavigationItem("home", Res.string.bottom_nav_bar_home, Icons.Filled.Home)
    data object Board : NavigationItem("board", Res.string.bottom_nav_bar_board, Icons.AutoMirrored.Filled.List)
    data object Settings : NavigationItem("settings", Res.string.bottom_nav_bar_settings, Icons.Filled.Settings)
}

val navigationList = listOf(
    NavigationItem.Home,
    NavigationItem.Board,
    NavigationItem.Settings
)