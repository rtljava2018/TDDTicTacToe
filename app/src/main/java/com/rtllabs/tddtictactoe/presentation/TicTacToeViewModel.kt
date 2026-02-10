package com.rtllabs.tddtictactoe.presentation

import androidx.lifecycle.ViewModel
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TicTacToeViewModel @Inject constructor(
    private val makeMoveUseCase: MakeMoveUseCase) : ViewModel() {


    private val _uiState = MutableStateFlow<GameUiState>(
        GameUiState.GameInProgress(
            board = List(3) { List(3) { null } },
            currentPlayer = Player.X
        )
    )
    val uiState: StateFlow<GameUiState> = _uiState

    fun onCellClicked(row: Int, col: Int) {
        val gameState = makeMoveUseCase(row, col)
        when {
            gameState.winner != null -> {
                _uiState.value = GameUiState.GameWon(
                    board = gameState.board,
                    winner = gameState.currentPlayer
                )
            }

            gameState.isDraw -> {
                _uiState.value = GameUiState.GameDraw(
                    board = gameState.board
                )
            }

            else -> {
                _uiState.value = GameUiState.GameInProgress(
                    board = gameState.board,
                    currentPlayer = gameState.currentPlayer
                )
            }
        }


    }

}