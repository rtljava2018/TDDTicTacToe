package com.rtllabs.tddtictactoe.presentation

import com.rtllabs.tddtictactoe.domain.entity.Player

sealed class GameUiState {
    data class GameInProgress(
        val board: List<List<Player?>>,
        val currentPlayer: Player) : GameUiState()
    data class GameWon(
        val board: List<List<Player?>>,
        val winner: Player) : GameUiState()
    data class GameDraw(val board: List<List<Player?>>) : GameUiState()

}