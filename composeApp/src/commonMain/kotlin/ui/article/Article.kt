package ui.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.Uuid
import moe.tlaster.precompose.koin.koinViewModel
import ui.components.HtmlView

@Composable
fun ArticleScreen(
    site: String,
    uuid: Uuid,
) {
    val viewModel = koinViewModel(ArticleViewModel::class)

    LaunchedEffect(key1 = Unit) {
        viewModel.setSiteData(site)
        viewModel.setUUIDData(uuid)
        viewModel.getArticleData()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        viewModel.article.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = it.value.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = it.value.writer,
                        )
                        Text(
                            text = it.value.date,
                        )
                    }
                }
                HtmlView(
                    it.value.content
                )
            }
        }
    }
}

