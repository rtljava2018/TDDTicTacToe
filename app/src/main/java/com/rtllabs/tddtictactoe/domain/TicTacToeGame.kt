package com.rtllabs.tddtictactoe.domain

class TicTacToeGame {
    private val board = Board()
    private var currentPlayer = Player.X


    fun getBoard(): Board {
        return board
    }

    fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    fun getWinner(): Player? {
        return null
    }

}