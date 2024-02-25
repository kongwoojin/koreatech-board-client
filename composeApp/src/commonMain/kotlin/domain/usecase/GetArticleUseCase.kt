package domain.usecase

import com.benasher44.uuid.Uuid
import domain.base.APIResult
import domain.model.Article
import domain.repository.ArticleRepository


class GetArticleUseCase(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(site: String, uuid: Uuid): APIResult<Article> {
        return articleRepository.getArticle(site, uuid)
    }
}
