package com.rtllabs.tddtictactoe.domain

import com.rtllabs.tddtictactoe.domain.entity.Board
import com.rtllabs.tddtictactoe.domain.entity.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BoardTest {
    private lateinit var board: Board

    @Before
    fun setup(){
      board = Board()
    }

    @Test
    fun gameNewBoardShouldHaveEmptyCells() {

        val isEmpty=board.isEmpty()
        val isFull= board.isBoardFull()

        assertTrue(isEmpty)
        assertFalse(isFull)
    }

    @Test
    fun gameBoardShouldReturnTrueWhenAllCellsIsFull() {
        for (i in 0 until 3){
            for (j in 0 until 3){
                board.setCells(i,j, Player.X)
            }
        }

        val isFull= board.isBoardFull()
        val isEmpty= board.isEmpty()

        assertTrue(isFull)
        assertFalse(isEmpty)
    }

    @Test
    fun setCellShouldPlacePlayersInEmptyCellReturnTrue() {

        val success=board.setCells(0,0, Player.X)

        assertTrue(success)
    }

    @Test
    fun setCellShouldNotPlacePlayersExistingCellReturnFalse() {

        board.setCells(0,0, Player.X)
        val isPlaced=board.setCells(0,0, Player.O)

        assertFalse(isPlaced)
    }

    @Test
    fun getCellShouldReturnPlayerInExistingCell() {

        board.setCells(0,0, Player.X)
        val player=board.getCell(0,0)

        assertEquals(Player.X,player)
    }

    @Test
    fun getAllCellsShouldReturnAllCells() {

        val cells= board.getAllCells()

        assertEquals(3,cells.size)

    }

}