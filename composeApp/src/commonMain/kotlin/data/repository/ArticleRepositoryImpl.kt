package data.repository

import com.benasher44.uuid.Uuid
import data.mapper.mapToArticle
import data.model.ArticleResponse
import data.remote.ArticleRemoteDataSource
import domain.base.APIResult
import domain.model.Article
import domain.repository.ArticleRepository
import io.ktor.client.call.*

class ArticleRepositoryImpl(private val articleRemoteDataSource: ArticleRemoteDataSource) :
    ArticleRepository {
    override suspend fun getArticle(site: String, uuid: Uuid): APIResult<Article> {
        val response = articleRemoteDataSource.getArticle(site, uuid)
        val data: ArticleResponse = response.body()
        return data.mapToArticle()
    }
}
