package data.model

import domain.model.BoardData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardResponse(
    @SerialName("last_page")
    val lastPage: Int,
    @SerialName("posts")
    val boardData: List<BoardResponseData>?
)

@Serializable
data class BoardResponseData(
    @SerialName("id")
    override val uuid: String,
    @SerialName("title")
    override val title: String,
    @SerialName("num")
    override val num: String,
    @SerialName("writer")
    override val writer: String,
    @SerialName("write_date")
    override val writeDate: String,
    @SerialName("read_count")
    override val read: Int
) : BoardData
