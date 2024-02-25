package domain.model

data class Article(
    val statusCode: Int = 200,
    val title: String = "",
    val writer: String = "",
    val content: String = "",
    val date: String = "",
    val articleUrl: String = "",
    val files: List<Files> = emptyList()
)
