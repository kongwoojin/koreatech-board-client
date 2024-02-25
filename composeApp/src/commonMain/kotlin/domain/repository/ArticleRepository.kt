package domain.repository

import com.benasher44.uuid.Uuid
import domain.base.APIResult
import domain.model.Article

interface ArticleRepository {
    suspend fun getArticle(site: String, uuid: Uuid): APIResult<Article>
}
