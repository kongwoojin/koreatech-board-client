package data.api

import com.benasher44.uuid.Uuid
import data.api.API
import data.api.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class APIImpl : API {
    private val ktorHttpClient by lazy { KtorHttpClient.ktorHttpClient }

    override suspend fun getBoard(site: String, board: String, page: Int, numOfItems: Int): HttpResponse {
        val response = ktorHttpClient.get {
            url("$site/$board")
            parameter("page", page)
            parameter("num_of_items", numOfItems)
        }

        return response.body()
    }

    override suspend fun getArticle(site: String, uuid: Uuid): HttpResponse {
        val response = ktorHttpClient.get {
            url("article/$site")
            parameter("uuid", uuid)
        }

        return response.body()
    }
}