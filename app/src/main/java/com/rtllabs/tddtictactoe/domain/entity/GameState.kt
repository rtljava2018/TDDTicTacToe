package com.rtllabs.tddtictactoe.domain.entity

data class GameState(
    val board: List<List<Player?>>,
    val currentPlayer: Player,
    val winner: Player?,
    val isDraw: Boolean,
    val isGameOver: Boolean
)

