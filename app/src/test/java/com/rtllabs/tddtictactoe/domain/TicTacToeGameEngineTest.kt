package com.rtllabs.tddtictactoe.domain

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.utils.TicTacToeConfig
import org.junit.Assert.*
import org.junit.Test

class TicTacToeGameEngineTest {
    @Test
    fun gameShouldStartWithEmptyBoard(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)
        val board = game.board


        assertTrue(board.isEmpty())

    }

    @Test
    fun gameShouldStartWithPlayerX() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun gameShouldStartWithNoWinner() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertNull(game.winner)
    }

    @Test
    fun gameShouldNotBeDrawInitially() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertFalse(game.isDraw)
    }

    @Test
    fun makeMoveShouldPlacePlayerInEmptyCell() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val gameState = game.makeMove(0, 0)

        assertTrue(gameState.board[0][0] == Player.X)
        assertEquals(Player.X,gameState.board[0][0])

    }

    @Test
    fun makeMoveShouldAllowPlacePlayerOccupiedCell() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)
        val gameState = game.makeMove(0, 0)

        assertFalse(gameState.board[0][0] == Player.O)
        assertNotEquals(Player.O,gameState.board[0][0])

    }

    @Test
    fun makeMoveShouldPlacePlayerAndAlternateTurnsToOtherPlayer() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val gameState = game.makeMove(0, 0)
        val currentPlayer = game.currentPlayer

        assertTrue(gameState.board[0][0] == Player.X)
        assertEquals(Player.O, currentPlayer)
    }

    @Test
    fun makeMoveShouldNotAllowAfterGameOver() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        val gameState=game.makeMove(2, 2)

        assertTrue(gameState.isGameOver)
    }

    @Test
    fun makeMoveShouldNotAllowAfterGameOver4X4() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 2)//X
        game.makeMove(0, 3)//X
        val gameState=game.makeMove(2, 2)

        assertTrue(gameState.isGameOver)
    }
    @Test
    fun makeMoveShouldNotAllowSwitchPlayerAfterGameOver() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X
        val isGameOver=game.makeMove(2, 2)//O

        assertTrue(isGameOver.isGameOver)
        assertEquals(Player.X, game.currentPlayer)
        assertNotEquals(Player.O, game.currentPlayer)

    }

    @Test
    fun checkWinnerShouldDetectRowWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)
        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        game.makeMove(0, 2)//X

        val winner = game.winner

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectColumnWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)


        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 1)//O
        game.makeMove(2, 0)//X
        val winner = game.winner

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectDiagonalWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 1)//X
        game.makeMove(1, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.winner


        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectOppositeDiagonalWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(2, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(1, 1)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 2)//X
        val winner = game.winner

        assertEquals(Player.X, winner)
    }

    @Test
    fun checkWinnerShouldDetectNoWinnerWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.winner

        assertNull(winner)
    }

    @Test
    fun evaluateGameStateShouldDetectDraw() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val winner = game.winner

        assertNull(winner)
    }

    @Test
    fun isGameOverShouldDetectAfterDraw() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.makeMove(0, 0)//X
        game.makeMove(0, 1)//O
        game.makeMove(0, 2)//X
        game.makeMove(1, 1)//O
        game.makeMove(1, 0)//X
        game.makeMove(1, 2)//O
        game.makeMove(2, 1)//X
        game.makeMove(2, 0)//O
        game.makeMove(2, 2)//X
        val isDraw = game.isDraw

        assertTrue(isDraw)
    }

    @Test
    fun checkWinnerByRowShouldDetectRowAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(1,0, Player.X)
        game.board.setCells(1,1, Player.X)
        game.board.setCells(1,2, Player.X)

        val winner=game.checkWinnerByRow(game.board.getAllCells(),1,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByRowShouldDetectRowAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(1,0, Player.X)
        game.board.setCells(1,1, Player.O)
        game.board.setCells(1,2, Player.X)

        val winner=game.checkWinnerByRow(game.board.getAllCells(),1,Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByColumnShouldDetectRowAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(0,2, Player.X)
        game.board.setCells(1,2, Player.X)
        game.board.setCells(2,2, Player.X)

        val winner=game.checkWinnerByColumn(game.board.getAllCells(),2,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByColumnShouldDetectRowAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(0,2, Player.X)
        game.board.setCells(1,2, Player.O)
        game.board.setCells(2,2, Player.X)

        val winner=game.checkWinnerByColumn(game.board.getAllCells(),2,Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByMainDiagonalShouldDetectRowAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(0,0, Player.X)
        game.board.setCells(1,1, Player.X)
        game.board.setCells(2,2, Player.X)

        val winner=game.checkWinnerByMainDiagonal(game.board.getAllCells(),Player.X)

        assertEquals(Player.X,winner)
    }
    @Test
    fun checkWinnerByMainDiagonalShouldDetectRowAndReturnWinnerFor4X4(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        for (index in 0 until TicTacToeConfig.FOUR_BY_FOUR_SIZE){
            game.board.setCells(index,index, Player.X)
        }

        val winner=game.checkWinnerByMainDiagonal(game.board.getAllCells(),Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByMainDiagonalShouldDetectRowAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(0,0, Player.X)
        game.board.setCells(1,1, Player.O)
        game.board.setCells(2,2, Player.X)

        val winner=game.checkWinnerByMainDiagonal(game.board.getAllCells(),Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByAntiDiagonalShouldDetectRowAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(2,0, Player.X)
        game.board.setCells(1,1, Player.X)
        game.board.setCells(0,2, Player.X)

        val winner=game.checkWinnerByAntiDiagonal(game.board.getAllCells(),Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByAntiDiagonalShouldDetectRowAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.board.setCells(2,0, Player.X)
        game.board.setCells(1,1, Player.O)
        game.board.setCells(0,2, Player.X)

        val winner=game.checkWinnerByAntiDiagonal(game.board.getAllCells(),Player.X)

        assertNull(winner)
    }

}