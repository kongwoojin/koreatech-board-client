package ui.home

data class HomeState(
    val isLoaded: Boolean = false,
    val isSuccess: Boolean = true,
    val statusCode: Int = 200,
    val error: String = ""
)
