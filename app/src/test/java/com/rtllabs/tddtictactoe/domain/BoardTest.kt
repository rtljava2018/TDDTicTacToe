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
                board.setCells(i,j,Player.X)
            }
        }

        val isFull= board.isBoardFull()
        val isEmpty= board.isEmpty()

        assertTrue(isFull)
        assertFalse(isEmpty)

    }

    @Test
    fun setCellShouldPlacePlayersInEmptyCellReturnTrue() {
        val board = Board()

        val success=board.setCells(0,0,Player.X)

        assertTrue(success)

    }

    @Test
    fun setCellShouldNotPlacePlayersExistingCellReturnFalse() {
        val board = Board()

        board.setCells(0,0,Player.X)
        val isPlaced=board.setCells(0,0,Player.O)

        assertFalse(isPlaced)

    }

    @Test
    fun getCellShouldReturnPlayerInExistingCell() {
        val board = Board()

        board.setCells(0,0,Player.X)
        val player=board.getCell(0,0)

        assertEquals(Player.X,player)

    }






}