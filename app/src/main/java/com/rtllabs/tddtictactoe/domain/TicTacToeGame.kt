package com.rtllabs.tddtictactoe.domain

class TicTacToeGame {
    private val board = Board()

    fun getBoard(): Board {
        return board
    }

    fun getCurrentPlayer(): Player {
        return Player.X
    }

}