package com.rtllabs.tddtictactoe.domain.engine

import com.rtllabs.tddtictactoe.domain.entity.Board
import com.rtllabs.tddtictactoe.domain.entity.GameState
import com.rtllabs.tddtictactoe.domain.entity.Player

class TicTacToeGameEngine {
    var board = Board()
        private set
    var currentPlayer = Player.X
        private set
    var winner: Player? = null
        private set
    var isDraw = false
        private set


    fun makeMove(row: Int, column: Int): GameState {
        if (isGameOver()) {
            return snapshot()
        }
        val isMark=board.setCells(row, column, currentPlayer)
        if (!isMark) return snapshot()
        evaluateGameState(row,column)
        if(!isGameOver()) {
            switchPlayer()
        }
        return snapshot()
    }

    private fun evaluateGameState(row: Int, column: Int) {
        winner = checkWinnerAfterMove(row,column)
        if (winner == null && board.isBoardFull()) {
            isDraw = true
        }
    }

    private fun isGameOver(): Boolean {
        return winner != null || isDraw
    }


    /*Deprecated*/
    @Deprecated("Moved to optimised logic in checkWinnerAfterMove")
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

    private fun checkWinnerAfterMove(row: Int, column: Int): Player? {
        val cells = board.getAllCells()
        val player = cells[row][column] ?: return null

        checkWinnerByRow(cells, row, player)?.let { return it }

        checkWinnerByColumn(cells, column, player)?.let { return it }

        if (row == column) {
            checkWinnerByMainDiagonal(cells, player)?.let { return it }
        }

        if (row + column == cells.size - 1) {
            checkWinnerByAntiDiagonal(cells, player)?.let { return it }
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

    fun checkWinnerByMainDiagonal(cells: List<List<Player?>>, player: Player): Player? {
        for (index in cells.indices) {
            if (cells[index][index] != player) return null
        }
        return player
    }

    fun checkWinnerByAntiDiagonal(cells: List<List<Player?>>, player: Player): Player? {
        val size = cells.size
        for (index in 0 until size) {
            if (cells[index][size - 1 - index] != player) return null
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