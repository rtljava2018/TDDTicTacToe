package com.rtllabs.tddtictactoe.domain

import org.junit.Assert.*
import org.junit.Test

class BoardTest {

    @Test
    fun gameNewBoardShouldHaveEmptyCells() {
        val board = Board()

        val isEmpty=board.isEmpty()
        val isFull= board.isBoardFull()

        assertTrue(isEmpty)
        assertFalse(isFull)
    }

    @Test
    fun gameBoardShouldReturnTrueWhenAllCellsIsFull() {
        val board = Board()
        for (i in 0 until 3){
            for (j in 0 until 3){
                board.cells[i][j] = Player.X
            }
        }

        val isFull= board.isBoardFull()
        val isEmpty= board.isEmpty()

        assertTrue(isFull)
        assertFalse(isEmpty)

    }






}