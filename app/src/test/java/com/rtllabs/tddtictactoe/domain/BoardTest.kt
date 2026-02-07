package com.rtllabs.tddtictactoe.domain

import org.junit.Assert.*
import org.junit.Test

class BoardTest {

    @Test
    fun gameNewBoardShouldHaveEmptyCells() {
        val board = Board()

        assertTrue(board.isEmpty())
    }

    @Test
    fun gameNewBoardShouldIsFullReturnFalseInitially(){
        val board = Board()

        val isFull= board.isBoardFull()

        assertFalse(isFull)

    }



}