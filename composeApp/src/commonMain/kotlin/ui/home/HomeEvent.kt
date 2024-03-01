package ui.home

import ui.base.UiEvent

sealed class HomeEvent : UiEvent {
    data object Loading : HomeEvent()
    data class Success(val statusCode: Int) : HomeEvent()
    data class Error(val statusCode: Int, val errorMessage: String) : HomeEvent()
    data class Failed(val errorMessage: String) : HomeEvent()
}