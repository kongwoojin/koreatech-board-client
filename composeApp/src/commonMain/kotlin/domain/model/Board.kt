package domain.model

data class Board (
    val lastPage: Int,
    val statusCode: Int,
    val boardData: List<BoardData>
)

interface BoardData {
    val uuid: String
    val title: String
    val num: String
    val writer: String
    val writeDate: String
    val read: Int
}
