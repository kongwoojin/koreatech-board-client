package ui.article

import com.benasher44.uuid.Uuid
import ui.base.UiState

data class ArticleState(
    val isLoaded: Boolean = false,
    val isSuccess: Boolean = true,
    val statusCode: Int = 200,
    val errorMessage: String = "",
    val url: String = "",
    val site: String = "",
    val uuid: Uuid = Uuid(0, 0)
): UiState