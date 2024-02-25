package data.api

import com.benasher44.uuid.Uuid
import io.ktor.client.statement.HttpResponse

interface API {
    suspend fun getBoard(
        site: String,
        board: String,
        page: Int = 1,
        numOfItems: Int = 20
    ): HttpResponse

    suspend fun getArticle(
        site: String,
        uuid: Uuid
    ): HttpResponse
}
