import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.benasher44.uuid.uuid4
import com.benasher44.uuid.uuidFrom
import koreatechboard.composeapp.generated.resources.Res
import koreatechboard.composeapp.generated.resources.app_name
import koreatechboard.composeapp.generated.resources.navigate_up
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import routes.navigationList
import ui.article.ArticleScreen
import ui.home.Home

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun NavigatorRoot() {
    val navigator = rememberNavigator()
    val isArticle = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(Res.string.app_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                if (isArticle.value) {
                    IconButton(onClick = {
                        navigator.goBack()
                        isArticle.value = false
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.navigate_up)
                        )
                    }
                }
            }
        )
    }, bottomBar = {
        if (getPlatform().isMobile) {
            if (!isArticle.value) {
                BottomNavigation(navigator)
            }
        }
    }) {
        Row {
            if (!getPlatform().isMobile) {
                if (!isArticle.value) {
                    NavigationRail(navigator, it)
                }
            }
            Navigator(navigator, it, isArticle)
        }
    }

}

@Composable
fun Navigator(navigator: Navigator, paddingValues: PaddingValues, isArticle: MutableState<Boolean>) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/home",
    ) {
        scene(
            route = "/home",
            navTransition = NavTransition(),
        ) {
            Home(
                openArticle = { site, uuid ->
                    isArticle.value = true
                    navigator.navigate(
                        "/article/$site/$uuid"
                    )
                }
            )
        }

        scene(
            route = "/board",
            navTransition = NavTransition(),
        ) {
            Text("Board")
        }

        scene(
            route = "/settings",
            navTransition = NavTransition(),
        ) {
            Text("Settings")
        }

        scene(
            route = "/article/{site}/{uuid}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            val site: String = backStackEntry.path<String>("site") ?: "school"
            val uuid: String = backStackEntry.path<String>("uuid") ?: uuid4().toString()

            ArticleScreen(
                site = site,
                uuid = uuidFrom(uuid)
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavigation(navigator: Navigator) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        navigationList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = stringResource(item.stringResource)) },
                label = { Text(stringResource(item.stringResource)) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navigator.navigate(
                        "/${navigationList[selectedItem].route}"
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NavigationRail(navigator: Navigator, paddingValues: PaddingValues) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationRail(modifier = Modifier.padding(paddingValues)) {
        navigationList.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = stringResource(item.stringResource)) },
                label = { Text(stringResource(item.stringResource)) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navigator.navigate(
                        "/${navigationList[selectedItem].route}"
                    )
                }
            )
        }
    }
}
