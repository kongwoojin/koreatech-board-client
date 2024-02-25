package data.remote

import data.api.API
import io.ktor.client.statement.HttpResponse

class BoardRemoteDataSource(private val api: API) {
    suspend fun getBoardMinimum(site: String, board: String): HttpResponse {
        return api.getBoard(site = site, board = board, numOfItems = 5)
    }
}