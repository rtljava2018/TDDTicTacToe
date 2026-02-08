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
}