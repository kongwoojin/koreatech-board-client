package ui.home

import domain.base.APIResult
import domain.model.BoardData
import domain.usecase.GetBoardMinimumUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeBoardViewModel(val getBoardMinimumUseCase: GetBoardMinimumUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    val boardData = mutableMapOf<String, List<BoardData>>()

    fun getApi(site: String, board: String, isReload: Boolean = false) {
        if (!boardData.containsKey(board) || isReload) {
            _uiState.update {
                it.copy(isLoaded = false)
            }
            boardData[board] = emptyList()
            viewModelScope.launch {
                runCatching {
                    getBoardMinimumUseCase(site, board)
                }.onSuccess {
                    _uiState.update { state ->
                        state.copy(isSuccess = true)
                    }
                    when (it) {
                        is APIResult.Success -> {
                            boardData[board] = it.data.boardData
                            _uiState.update { state ->
                                state.copy(statusCode = it.data.statusCode, isLoaded = true)
                            }
                        }

                        is APIResult.Error -> {
                            _uiState.update { state ->
                                state.copy(statusCode = it.errorType.statusCode, isLoaded = true)
                            }
                        }
                    }
                }.onFailure {
                    _uiState.update { state ->
                        state.copy(isLoaded = true, isSuccess = false, error = it.message ?: "")
                    }
                }
            }
        }
    }
}
