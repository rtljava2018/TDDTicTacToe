package com.rtllabs.tddtictactoe.domain

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.entity.Player
import org.junit.Assert.*
import org.junit.Test

class TicTacToeGameEngineTest {
    @Test
    fun gameShouldStartWithEmptyBoard(){
        val game = TicTacToeGameEngine()
        val board = game.getBoard()

        assertTrue(board.isEmpty())

    }

    @Test
    fun gameShouldStartWithPlayerX() {
        val game = TicTacToeGameEngine()

        assertEquals(Player.X, game.getCurrentPlayer())
    }

    @Test
    fun gameShouldStartWithNoWinner() {
        val game = TicTacToeGameEngine()

        assertNull(game.getWinner())
    }

    @Test
    fun gameShouldNotBeDrawInitially() {
        val game = TicTacToeGameEngine()

        assertFalse(game.isDraw())
    }

    @Test
    fun makeMoveShouldPlacePlayerInEmptyCell() {
        val game = TicTacToeGameEngine()

        val gameState = game.makeMove(0, 0)

        assertTrue(gameState.board[0][0] == Player.X)
        assertEquals(Player.X,gameState.board[0][0])

    }

    @Test
    fun makeMoveShouldAllowPlacePlayerOccupiedCell() {
        val game = TicTacToeGameEngine()

        game.makeMove(0, 0)
        val gameState = game.makeMove(0, 0)

        assertFalse(gameState.board[0][0] == Player.O)
        assertNotEquals(Player.O,gameState.board[0][0])

    }

    @Test
    fun makeMoveShouldPlacePlayerAndAlternateTurnsToOtherPlayer() {
        val game = TicTacToeGameEngine()

        val gameState = game.makeMove(0, 0)
        val currentPlayer = game.getCurrentPlayer()

        assertTrue(gameState.board[0][0] == Player.X)
        assertEquals(Player.O, currentPlayer)
    }

    @Test
    fun makeMoveShouldNotAllowAfterGameOver() {
        val game = TicTacToeGameEngine()

        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        val gameState=game.makeMove(2, 2)

        assertTrue(gameState.isGameOver)
    }
    @Test
    fun makeMoveShouldNotAllowSwitchPlayerAfterGameOver() {
        val game = TicTacToeGameEngine()

        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        val isGameOver=game.makeMove(2, 2)//O

        assertTrue(isGameOver.isGameOver)
        assertEquals(Player.X, game.getCurrentPlayer())
        assertNotEquals(Player.O, game.getCurrentPlayer())

    }

    @Test
    fun checkWinnerShouldDetectRowWin() {
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
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
        val game = TicTacToeGameEngine()
        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val isDraw = game.isDraw()

        assertTrue(isDraw)
    }




}