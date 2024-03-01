package ui.home

import domain.base.APIResult
import domain.model.BoardData
import domain.usecase.GetBoardMinimumUseCase
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.base.BaseViewModel

class HomeBoardViewModel(val getBoardMinimumUseCase: GetBoardMinimumUseCase) :
    BaseViewModel<HomeState, HomeEvent>(HomeState()) {
    val boardData = mutableMapOf<String, List<BoardData>>()

    fun getApi(site: String, board: String, isReload: Boolean = false) {
        if (!boardData.containsKey(board) || isReload) {
            sendEvent(HomeEvent.Loading)
            boardData[board] = emptyList()
            viewModelScope.launch {
                runCatching {
                    getBoardMinimumUseCase(site, board)
                }.onSuccess {
                    when (it) {
                        is APIResult.Success -> {
                            boardData[board] = it.data.boardData
                            sendEvent(HomeEvent.Success(it.data.statusCode))
                        }

                        is APIResult.Error -> {
                            sendEvent(HomeEvent.Error(it.errorType.statusCode, it.errorType.message))
                        }
                    }
                }.onFailure {
                    sendEvent(HomeEvent.Failed(it.message ?: ""))
                }
            }
        }
    }

    override fun reduce(oldState: HomeState, event: HomeEvent) {
        when (event) {
            is HomeEvent.Loading -> {
                setState(oldState.copy(isLoaded = false))
            }

            is HomeEvent.Success -> {
                setState(oldState.copy(isLoaded = true, isSuccess = true, statusCode = event.statusCode))
            }

            is HomeEvent.Error -> {
                setState(
                    oldState.copy(
                        isLoaded = true,
                        isSuccess = false,
                        statusCode = event.statusCode,
                        errorMessage = event.errorMessage
                    )
                )
            }

            is HomeEvent.Failed -> {
                setState(oldState.copy(isLoaded = true, isSuccess = false, errorMessage = event.errorMessage))
            }
        }
    }
}
