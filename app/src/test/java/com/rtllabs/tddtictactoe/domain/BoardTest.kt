package com.rtllabs.tddtictactoe.domain

import org.junit.Assert.assertTrue
import org.junit.Test

class BoardTest {

    @Test
    fun gameNewBoardShouldHaveEmptyCells() {
        val board = Board()

        assertTrue(board.isEmpty())
    }
}