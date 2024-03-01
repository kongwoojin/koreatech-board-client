package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("id")
    val uuid: String,
    @SerialName("title")
    val title: String,
    @SerialName("writer")
    val writer: String,
    @SerialName("content")
    val content: String,
    @SerialName("write_date")
    val date: String,
    @SerialName("article_url")
    val articleUrl: String,
    @SerialName("files")
    val files: List<FilesResponse> = emptyList(),
    @SerialName("error")
    val error: String
)
