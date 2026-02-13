package com.rtllabs.tddtictactoe.domain.engine

import com.rtllabs.tddtictactoe.domain.entity.Board
import com.rtllabs.tddtictactoe.domain.entity.GameState
import com.rtllabs.tddtictactoe.domain.entity.Player

class TicTacToeGameEngine: GameEngine {
    var board = Board()
        private set
    var currentPlayer = Player.X
        private set
    var winner: Player? = null
        private set
    var isDraw = false
        private set

    override fun initBoard(boardSize: Int){
        board.makeBoard(boardSize)
    }

    override fun makeMove(row: Int, column: Int): GameState {
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


    internal fun checkWinnerByRow(cells: List<List<Player?>>, row: Int, player: Player): Player? {
        for (column in cells.indices) {
            if (cells[row][column] != player) return null
        }
        return player
    }

    internal fun checkWinnerByColumn(cells: List<List<Player?>>, column: Int, player: Player): Player? {
        for (row in cells.indices) {
            if (cells[row][column] != player) return null
        }
        return player
    }

    internal fun checkWinnerByMainDiagonal(cells: List<List<Player?>>, player: Player): Player? {
        for (index in cells.indices) {
            if (cells[index][index] != player) return null
        }
        return player
    }

    internal fun checkWinnerByAntiDiagonal(cells: List<List<Player?>>, player: Player): Player? {
        val size = cells.size
        for (index in 0 until size) {
            if (cells[index][size - 1 - index] != player) return null
        }
        return player
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }

    override fun snapshot(): GameState {
        return GameState(
            board = board.getAllCells().map { it.toList() },
            currentPlayer = currentPlayer,
            winner = winner,
            isDraw = isDraw,
            isGameOver = isGameOver() )
    }


}