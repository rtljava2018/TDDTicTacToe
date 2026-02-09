package com.rtllabs.tddtictactoe.presentation

import androidx.lifecycle.ViewModel
import com.rtllabs.tddtictactoe.domain.entity.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<GameUiState>(
        GameUiState.GameInProgress(
            board = List(3) { List(3) { null } },
            currentPlayer = Player.X
        )
    )
    val uiState: StateFlow<GameUiState> = _uiState

}