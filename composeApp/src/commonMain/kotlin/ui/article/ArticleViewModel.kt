package ui.article

import androidx.compose.runtime.mutableStateOf
import ui.base.BaseViewModel
import com.benasher44.uuid.Uuid
import domain.base.APIResult
import domain.model.Article
import domain.usecase.GetArticleUseCase
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

class ArticleViewModel(private val getArticleUseCase: GetArticleUseCase) : BaseViewModel<ArticleState, ArticleEvent>(ArticleState()) {
    val article = mutableStateOf(Article())

    fun getArticleData() {
        viewModelScope.launch {
            sendEvent(ArticleEvent.Loading)
            runCatching {
                getArticleUseCase(uiState.value.site, uiState.value.uuid)
            }.onSuccess {
                when (it) {
                    is APIResult.Success -> {
                        sendEvent(ArticleEvent.Success(it.data.statusCode, it.data.articleUrl))
                        article.value = it.data
                    }

                    is APIResult.Error -> {
                        sendEvent(ArticleEvent.Error(it.errorType.statusCode, it.errorType.message))
                    }
                }
            }.onFailure {
                sendEvent(ArticleEvent.Failed(it.message ?: ""))
            }
        }
    }

    fun setSiteData(site: String) {
        setState(uiState.value.copy(site = site))
    }

    fun setUUIDData(uuid: Uuid) {
        setState(uiState.value.copy(uuid = uuid))
    }

    override fun reduce(oldState: ArticleState, event: ArticleEvent) {
        when (event) {
            ArticleEvent.Loading -> {
                setState(oldState.copy(isLoaded = false))
            }
            is ArticleEvent.Error -> {
                setState(oldState.copy(isLoaded = true, isSuccess = false, statusCode = event.statusCode, errorMessage = event.errorMessage))
            }
            is ArticleEvent.Failed -> {
                setState(oldState.copy(isLoaded = true, isSuccess = false, errorMessage = event.errorMessage))
            }
            is ArticleEvent.Success -> {
                setState(oldState.copy(isLoaded = true, isSuccess = true, statusCode = event.statusCode, url = event.articleUrl))
            }
        }
    }
}
