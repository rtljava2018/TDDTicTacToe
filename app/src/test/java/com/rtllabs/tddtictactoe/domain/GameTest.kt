package com.rtllabs.tddtictactoe.domain

import org.junit.Assert.*
import org.junit.Test

class GameTest {
    @Test
    fun gameShouldStartWithEmptyBoard(){
        val game = TicTacToeGame()
        val board = game.getBoard()

        assertTrue(board.isEmpty())

    }

    @Test
    fun gameShouldStartWithPlayerX() {
        val game = TicTacToeGame()

        assertEquals(Player.X, game.getCurrentPlayer())
    }

    @Test
    fun gameShouldStartWithNoWinner() {
        val game = TicTacToeGame()

        assertNull(game.getWinner())
    }

    @Test
    fun gameShouldNotBeDrawInitially() {
        val game = TicTacToeGame()

        assertFalse(game.isDraw())
    }

    @Test
    fun makeMoveShouldPlacePlayerInEmptyCell() {
        val game = TicTacToeGame()

        val isMark = game.makeMove(0, 0)

        assertTrue(isMark)

    }

    @Test
    fun makeMoveShouldAllowPlacePlayerOccupiedCell() {
        val game = TicTacToeGame()

        game.makeMove(0, 0)
        val isMark = game.makeMove(0, 0)

        assertFalse(isMark)

    }

    @Test
    fun makeMoveShouldPlacePlayerAndAlternateTurnsToOtherPlayer() {
        val game = TicTacToeGame()

        val isMark = game.makeMove(0, 0)
        val currentPlayer = game.getCurrentPlayer()

        assertTrue(isMark)
        assertEquals(Player.O, currentPlayer)
    }

    @Test
    fun checkWinnerShouldDetectRowWin() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        val winner = game.getWinner()

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectColumnWin() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 1)//O
        game.makeMove(2, 0)//X
        val winner = game.getWinner()

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectDiagonalWin() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 1)//X
        game.makeMove(1, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.getWinner()

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectOppositeDiagonalWin() {
        val game = TicTacToeGame()
        game.makeMove(2, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 1)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 2)//X
        val winner = game.getWinner()

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectNoWinnerWin() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.getWinner()

        assertNull(winner)
    }

    @Test
    fun evaluateGameStateShouldDetectDraw() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.getWinner()

        assertNull(winner)
    }

    @Test
    fun isGameOverShouldDetectAfterDraw() {
        val game = TicTacToeGame()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val isDraw = game.isGameOver()

        assertTrue(isDraw)
    }


}