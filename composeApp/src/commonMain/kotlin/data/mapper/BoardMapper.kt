package data.mapper

import data.model.BoardResponse
import domain.base.APIResult
import domain.base.ErrorType
import domain.model.Board

fun BoardResponse.mapToBoard(): APIResult<Board> {
    return if (this.statusCode == 200) {
        APIResult.Success(
            Board(
                lastPage = this.lastPage,
                statusCode = this.statusCode,
                boardData = this.boardData ?: emptyList()
            )
        )
    } else {
        APIResult.Error(ErrorType(this.statusCode, this.error))
    }
}
