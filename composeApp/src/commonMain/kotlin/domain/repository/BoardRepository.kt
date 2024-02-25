package domain.repository

import domain.base.APIResult
import domain.model.Board

interface BoardRepository {
    suspend fun getBoardMinimum(site: String, board: String): APIResult<Board>
}