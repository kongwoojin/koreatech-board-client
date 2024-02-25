package ui.article

import androidx.compose.runtime.mutableStateOf
import com.benasher44.uuid.Uuid
import domain.base.APIResult
import domain.model.Article
import domain.usecase.GetArticleUseCase
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope


class ArticleViewModel constructor(
    private val getArticleUseCase: GetArticleUseCase
) : ViewModel() {
    val article = mutableStateOf(Article())

    private val uuid = mutableStateOf(Uuid(0, 0))

    private val site = mutableStateOf("")

    private val isLoading = mutableStateOf(false)

    private val statusCode = mutableStateOf(200)

    private val url = mutableStateOf("")

    fun getArticleData() {
        viewModelScope.launch {
            isLoading.value = true

            runCatching {
                getArticleUseCase(site.value, uuid.value)
            }.onSuccess {
                when (it) {
                    is APIResult.Success -> {
                        article.value = it.data
                        statusCode.value = it.data.statusCode
                        url.value = it.data.articleUrl
                    }

                    is APIResult.Error -> {
                        statusCode.value = it.errorType.statusCode
                    }
                }
            }.onFailure {
                println(it.message)
            }

            isLoading.value = false
        }
    }

    fun setSiteData(site: String) {
        this.site.value = site
    }

    fun setUUIDData(uuid: Uuid) {
        this.uuid.value = uuid
    }
}
