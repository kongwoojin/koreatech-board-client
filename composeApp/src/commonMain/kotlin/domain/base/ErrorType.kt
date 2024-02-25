package domain.base

data class ErrorType(
    val statusCode: Int = 200,
    val message: String = ""
)
