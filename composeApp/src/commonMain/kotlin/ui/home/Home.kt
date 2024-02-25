package ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import getPlatform
import koreatechboard.composeapp.generated.resources.Res
import koreatechboard.composeapp.generated.resources.app_name
import koreatechboard.composeapp.generated.resources.error_retry
import koreatechboard.composeapp.generated.resources.error_server_down
import kotlinx.coroutines.launch
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import routes.Department
import ui.theme.notoSansKR

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Home(
    openArticle: (String, String) -> Unit = { _, _ -> }
) {
    if (getPlatform().isMobile) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            BoardInMain(department = Department.School, openArticle)
            BoardInMain(department = Department.Dorm, openArticle)
        }
    } else {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            BoardInMain(department = Department.School, openArticle)
            BoardInMain(department = Department.Dorm, openArticle)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun BoardInMain(
    department: Department,
    openArticle: (String, String) -> Unit = { _, _ -> }
) {
    val viewModel = koinViewModel(
        vmClass = HomeBoardViewModel::class,
        key = department.name)

    val key = remember {
        mutableStateOf(department.boards[0].board)
    }

    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        department.boards.size
    }

    val coroutineScope = rememberCoroutineScope()

    val tabIndex = pagerState.currentPage

    LaunchedEffect(key1 = tabIndex) {
        viewModel.getApi(department.name, key.value)
    }

    val uiState = viewModel.uiState.collectAsState().value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(text = stringResource(department.stringResource), fontSize = 16.sp)
            }

            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                containerColor = Color.Transparent,
                edgePadding = 0.dp
            ) {
                department.boards.forEachIndexed { index, board ->
                    Tab(
                        text = { Text(text = stringResource(board.stringResource)) },
                        selected = tabIndex == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { page ->
                key.value = department.boards[page].board
                coroutineScope.launch {
                    viewModel.getApi(department.name, key.value)
                }

                if (!uiState.isLoaded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (uiState.isSuccess && uiState.statusCode == 200) {
                        Column {
                            viewModel.boardData[key.value]?.forEach { data ->
                                Box(
                                    modifier = Modifier
                                        .clickable {
                                            openArticle(department.name, data.uuid)
                                        }
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        text = data.title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                            }
                        }
                    } else if (uiState.isSuccess  && uiState.statusCode != 200) {
                        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Text(
                                text = stringResource(Res.string.error_server_down)
                            )
                            Button(onClick = {
                                viewModel.getApi(department.name, key.value, true)
                            }) {
                                Text(text = stringResource(Res.string.error_retry))
                            }
                        }
                    } else if (!uiState.isSuccess) {
                        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Text(text = uiState.statusCode.toString())
                            Button(onClick = {

                            }) {
                                Text(text = "!")
                            }
                        }
                    }
                }
            }
        }
    }
}
