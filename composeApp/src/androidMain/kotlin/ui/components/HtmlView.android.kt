package ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import be.digitalia.compose.htmlconverter.htmlToAnnotatedString

@OptIn(ExperimentalTextApi::class)
@Composable
actual fun HtmlView(html: String) {
    val convertedText = remember(html) { htmlToAnnotatedString(html) }
    CustomClickableText(
        text = convertedText,
        modifier = Modifier.fillMaxWidth().padding(16.dp),

        onClick = { position ->
            convertedText
                .getUrlAnnotations(position, position)
                .firstOrNull()?.let { range -> println(range.item.url) }
        }
    )
}