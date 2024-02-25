package data.repository

import data.remote.BoardRemoteDataSource
import data.mapper.mapToBoard
import data.model.BoardResponse
import domain.base.APIResult
import domain.model.Board
import domain.repository.BoardRepository
import io.ktor.client.call.body

class BoardRepositoryImpl(private val boardRemoteDataSource: BoardRemoteDataSource) : BoardRepository {
    override suspend fun getBoardMinimum(site: String, board: String): APIResult<Board> {
        val response = boardRemoteDataSource.getBoardMinimum(site, board)
        val data: BoardResponse = response.body()
        return data.mapToBoard(response.status.value)
    }
}