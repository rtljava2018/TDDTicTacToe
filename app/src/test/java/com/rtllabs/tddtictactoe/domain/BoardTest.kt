package com.rtllabs.tddtictactoe.domain

import com.rtllabs.tddtictactoe.domain.entity.Board
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.utils.TicTacToeConfig
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
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val isEmpty=board.isEmpty()
        val isFull= board.isBoardFull()

        assertTrue(isEmpty)
        assertFalse(isFull)
    }

    @Test
    fun gameBoardShouldReturnTrueWhenAllCellsIsFull() {
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        for (row in 0 until TicTacToeConfig.TIC_TAC_TOE_SIZE){
            for (column in 0 until TicTacToeConfig.TIC_TAC_TOE_SIZE){
                board.setCells(row,column, Player.X)
            }
        }

        val isFull= board.isBoardFull()
        val isEmpty= board.isEmpty()

        assertTrue(isFull)
        assertFalse(isEmpty)
    }

    @Test
    fun setCellShouldPlacePlayersInEmptyCellReturnTrue() {
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val success=board.setCells(0,0, Player.X)

        assertTrue(success)
    }

    @Test
    fun setCellShouldNotPlacePlayersExistingCellReturnFalse() {
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        board.setCells(0,0, Player.X)
        val isPlaced=board.setCells(0,0, Player.O)

        assertFalse(isPlaced)
    }

    @Test
    fun getCellShouldReturnPlayerInExistingCell() {
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        board.setCells(0,0, Player.X)
        val player=board.getCell(0,0)

        assertEquals(Player.X,player)
    }

    @Test
    fun getAllCellsShouldReturnAllCells3X3() {
        board.makeBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val cells= board.getAllCells()

        assertEquals(TicTacToeConfig.TIC_TAC_TOE_SIZE,cells.size)

    }

    @Test
    fun getAllCellsShouldReturnAllCellsFor4x4() {
        board.makeBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        val cells= board.getAllCells()

        assertEquals(TicTacToeConfig.FOUR_BY_FOUR_SIZE,cells.size)

    }
    @Test
    fun getAllCellsShouldReturnAllCellsFor5x5() {
        board.makeBoard(TicTacToeConfig.FIVE_BY_FIVE_SIZE)

        val cells= board.getAllCells()

        assertEquals(TicTacToeConfig.FIVE_BY_FIVE_SIZE,cells.size)

    }

}