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
    fun makeMoveShouldNotPlacePlayerInExistingCell() {
        val game = TicTacToeGame()

        game.makeMove(0, 0)
        val isMark = game.makeMove(0, 0)

        assertFalse(isMark)

    }




}