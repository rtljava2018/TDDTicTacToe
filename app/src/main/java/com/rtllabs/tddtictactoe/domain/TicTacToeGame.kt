package com.rtllabs.tddtictactoe.domain

class TicTacToeGame {
    private val board = Board()
    private var currentPlayer = Player.X
    private var winner: Player? = null
    private var isDraw = false


    fun getBoard(): Board {
        return board
    }

    fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    fun getWinner(): Player? {
        return winner
    }

    fun isDraw(): Boolean {
        return isDraw
    }

    fun makeMove(row: Int, col: Int): Boolean {
        val isMark=board.setCells(row, col, currentPlayer)
        switchPlayer()
        return isMark
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }


}