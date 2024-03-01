package ui.home

import ui.base.UiState

data class HomeState(
    val isLoaded: Boolean = false,
    val isSuccess: Boolean = true,
    val statusCode: Int = 200,
    val errorMessage: String = ""
) : UiState