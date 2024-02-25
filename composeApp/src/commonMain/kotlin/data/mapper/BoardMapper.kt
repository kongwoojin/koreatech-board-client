package data.mapper

import data.model.BoardResponse
import domain.base.APIResult
import domain.base.ErrorType
import domain.model.Board

fun BoardResponse.mapToBoard(code: Int): APIResult<Board> {
    return if (code == 200) {
        APIResult.Success(
            Board(
                lastPage = this.lastPage,
                statusCode = code,
                boardData = this.boardData ?: emptyList()
            )
        )
    } else {
        APIResult.Error(ErrorType(code))
    }
}
