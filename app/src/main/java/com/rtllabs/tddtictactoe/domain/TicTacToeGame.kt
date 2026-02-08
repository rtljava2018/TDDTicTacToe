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
        if (isGameOver()) {
            return false
        }
        val isMark=board.setCells(row, col, currentPlayer)
        if (!isMark) return false
        evaluateGameState()
        if(!isGameOver()) {
            switchPlayer()
        }
        return true
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

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }


}