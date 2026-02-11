package com.rtllabs.tddtictactoe.domain.engine

import com.rtllabs.tddtictactoe.domain.entity.Board
import com.rtllabs.tddtictactoe.domain.entity.GameState
import com.rtllabs.tddtictactoe.domain.entity.Player

class TicTacToeGameEngine {
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

    fun makeMove(row: Int, col: Int): GameState {
        if (isGameOver()) {
            return snapshot()
        }
        val isMark=board.setCells(row, col, currentPlayer)
        if (!isMark) return snapshot()
        evaluateGameState()
        if(!isGameOver()) {
            switchPlayer()
        }
        return snapshot()
    }

    private fun evaluateGameState() {
        winner = checkWinner()
        if (winner == null && board.isBoardFull()) {
            isDraw = true
        }
    }

    private fun isGameOver(): Boolean {
        return winner != null || isDraw
    }


    private fun checkWinner(): Player? {
        val cells = board.getAllCells()

        //rows
        for (i in 0..2) {
            if (cells[i][0] != null && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) {
                return cells[i][0]
            }
        }

        //columns
        for (j in 0..2) {
            if (cells[0][j] != null && cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j]) {
                return cells[0][j]
            }
        }

        //diagonals
        if (cells[0][0] != null && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return cells[0][0]
        }
        if (cells[0][2] != null && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return cells[0][2]
        }

        return null
    }

    fun checkWinnerByRow(cells: List<List<Player?>>, row: Int, player: Player): Player? {
        for (column in cells.indices) {
            if (cells[row][column] != player) return null
        }
        return player
    }

    fun checkWinnerByColumn(cells: List<List<Player?>>, column: Int, player: Player): Player? {
        for (row in cells.indices) {
            if (cells[row][column] != player) return null
        }
        return player
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }

    private fun snapshot(): GameState {
        return GameState(
            board = board.getAllCells().map { it.toList() },
            currentPlayer = currentPlayer,
            winner = winner,
            isDraw = isDraw,
            isGameOver = isGameOver() )
    }


}