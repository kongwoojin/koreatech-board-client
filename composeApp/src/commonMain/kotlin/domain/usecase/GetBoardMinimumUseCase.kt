package domain.usecase

import domain.base.APIResult
import domain.model.Board
import domain.repository.BoardRepository

class GetBoardMinimumUseCase (private val boardRepository: BoardRepository) {
    suspend operator fun invoke(site: String, board: String): APIResult<Board> {
        return boardRepository.getBoardMinimum(site = site, board = board)
    }
}
