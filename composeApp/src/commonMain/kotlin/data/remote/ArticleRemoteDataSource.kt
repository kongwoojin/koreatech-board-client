package data.remote

import com.benasher44.uuid.Uuid
import data.api.API
import io.ktor.client.statement.*


class ArticleRemoteDataSource(private val api: API) {
    suspend fun getArticle(site: String, uuid: Uuid): HttpResponse {
        return api.getArticle(site, uuid)
    }
}
