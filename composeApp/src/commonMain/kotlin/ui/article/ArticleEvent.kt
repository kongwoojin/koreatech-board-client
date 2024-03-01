package ui.article

import ui.base.UiEvent

sealed class ArticleEvent : UiEvent {
    data object Loading : ArticleEvent()
    data class Success(val statusCode: Int, val articleUrl: String) : ArticleEvent()
    data class Error(val statusCode: Int, val errorMessage: String) : ArticleEvent()
    data class Failed(val errorMessage: String) : ArticleEvent()
}