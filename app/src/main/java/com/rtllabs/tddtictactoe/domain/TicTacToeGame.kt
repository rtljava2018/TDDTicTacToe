package com.rtllabs.tddtictactoe.domain

import kotlin.collections.get

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
        checkWinner()
        switchPlayer()
        return isMark
    }

    private fun checkWinner() {
        val cells = board.getAllCells()

        //rows
        for (i in 0..2) {
            if (cells[i][0] != null && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) {
                winner= cells[i][0]
            }
        }

        //columns
        for (j in 0..2) {
            if (cells[0][j] != null && cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j]) {
                winner = cells[0][j]
            }
        }

        //diagonals
        if (cells[0][0] != null && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            winner = cells[0][0]
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }


}