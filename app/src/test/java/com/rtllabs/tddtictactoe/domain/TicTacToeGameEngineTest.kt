package com.rtllabs.tddtictactoe.domain

import com.rtllabs.tddtictactoe.domain.engine.GameEngine
import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.utils.TicTacToeConfig
import com.rtllabs.tddtictactoe.utils.TicTacToeConfig.TIC_TAC_TOE_SIZE
import org.junit.Assert.*
import org.junit.Test

class TicTacToeGameEngineTest {

    @Test
    fun gameShouldStartWithInitializedBoard() {
        val game: GameEngine = TicTacToeGameEngine()
        val state=game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertEquals(TIC_TAC_TOE_SIZE,state.board.size)
        assertEquals(Player.X,state.currentPlayer)
        assertFalse(state.isGameOver)
        assertFalse(state.isDraw)
        assertNull(state.winner)
        assertTrue(state.board.flatten().all { it == null })
    }

    @Test
    fun gameShouldStartWithPlayerXUsingGameEngine() {
        val game: GameEngine = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        val state=game.makeMove(0,0)

        assertEquals(Player.X, state.board[0][0])
    }


    @Test
    fun gameShouldStartWithEmptyBoard(){
        val game = TicTacToeGameEngine()
        val state=game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)


        assertTrue(state.board.flatten().all { it == null })

    }

    @Test
    fun gameShouldStartWithPlayerX() {
        val game: GameEngine = TicTacToeGameEngine()
        val state=game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertEquals(Player.X, state.currentPlayer)
    }

    @Test
    fun gameShouldStartWithNoWinner() {
        val game = TicTacToeGameEngine()
        val state=game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertNull(state.winner)
    }

    @Test
    fun gameShouldNotBeDrawInitially() {
        val game = TicTacToeGameEngine()
        val state=game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        assertFalse(state.isDraw)
    }

    @Test
    fun makeMoveWithoutInitializationShouldThrowException() {
        val game = TicTacToeGameEngine()

        assertThrows(IllegalStateException::class.java){ game.makeMove(0,0)}
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
        val currentPlayer = gameState.currentPlayer

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
        val state=game.makeMove(2, 2)//O

        assertTrue(state.isGameOver)
        assertEquals(Player.X, state.currentPlayer)
        assertNotEquals(Player.O, state.currentPlayer)

    }

    @Test
    fun checkWinnerShouldDetectRowWin() {
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)
        game.makeMove(0, 0)//X
        game.makeMove(1, 0)//O
        game.makeMove(0, 1)//X
        game.makeMove(1, 1)//O
        val state=game.makeMove(0, 2)//X

        val winner = state.winner

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
        val state=game.makeMove(2, 0)//X
        val winner = state.winner

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
        val winner = game.snapshot().winner


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
        val winner = game.snapshot().winner

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
        val winner = game.snapshot().winner

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
        val winner = game.snapshot().winner

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
        val isDraw = game.snapshot().isDraw

        assertTrue(isDraw)
    }

    @Test
    fun checkWinnerByRowShouldDetectRowAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(1,0, Player.X)
        game.forceSetCell(1,1, Player.X)
        game.forceSetCell(1,2, Player.X)

        val winner=game.checkWinnerByRow(game.snapshot().board,1,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByRowShouldDetectRowAndReturnWinner4X4(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        val targetRow=1
        for (column in 0 until TicTacToeConfig.FOUR_BY_FOUR_SIZE){
            game.forceSetCell(targetRow,column, Player.X)
        }
        val winner=game.checkWinnerByRow(game.snapshot().board,1,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByRowShouldDetectRowAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(1,0, Player.X)
        game.forceSetCell(1,1, Player.O)
        game.forceSetCell(1,2, Player.X)

        val winner=game.checkWinnerByRow(game.snapshot().board,1,Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByColumnShouldDetectColumnAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(0,2, Player.X)
        game.forceSetCell(1,2, Player.X)
        game.forceSetCell(2,2, Player.X)

        val winner=game.checkWinnerByColumn(game.snapshot().board,2,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByColumnShouldDetectColumnAndReturnWinner4X4(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        val targetColumn=2
        for (row in 0 until TicTacToeConfig.FOUR_BY_FOUR_SIZE){
            game.forceSetCell(row,targetColumn, Player.X)
        }
        val winner=game.checkWinnerByColumn(game.snapshot().board,2,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByColumnShouldDetectColumnAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(0,2, Player.X)
        game.forceSetCell(1,2, Player.O)
        game.forceSetCell(2,2, Player.X)

        val winner=game.checkWinnerByColumn(game.snapshot().board,2,Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByMainDiagonalShouldDetectMainDiagonalAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(0,0, Player.X)
        game.forceSetCell(1,1, Player.X)
        game.forceSetCell(2,2, Player.X)

        val winner=game.checkWinnerByMainDiagonal(game.snapshot().board,Player.X)

        assertEquals(Player.X,winner)
    }
    @Test
    fun checkWinnerByMainDiagonalShouldDetectMainDiagonalAndReturnWinnerFor4X4(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        for (index in 0 until TicTacToeConfig.FOUR_BY_FOUR_SIZE){
            game.forceSetCell(index,index, Player.X)
        }

        val winner=game.checkWinnerByMainDiagonal(game.snapshot().board,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByMainDiagonalShouldDetectMainDiagonalAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(0,0, Player.X)
        game.forceSetCell(1,1, Player.O)
        game.forceSetCell(2,2, Player.X)

        val winner=game.checkWinnerByMainDiagonal(game.snapshot().board,Player.X)

        assertNull(winner)
    }

    @Test
    fun checkWinnerByAntiDiagonalShouldDetectAntiDiagonalAndReturnWinner(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(2,0, Player.X)
        game.forceSetCell(1,1, Player.X)
        game.forceSetCell(0,2, Player.X)

        val winner=game.checkWinnerByAntiDiagonal(game.snapshot().board,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByAntiDiagonalShouldDetectAntiDiagonalAndReturnWinner4X4(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.FOUR_BY_FOUR_SIZE)

        for (i in 0 until TicTacToeConfig.FOUR_BY_FOUR_SIZE) {
            game.forceSetCell(i, TicTacToeConfig.FOUR_BY_FOUR_SIZE - 1 - i, Player.X)
        }

        val winner=game.checkWinnerByAntiDiagonal(game.snapshot().board,Player.X)

        assertEquals(Player.X,winner)
    }

    @Test
    fun checkWinnerByAntiDiagonalShouldDetectAntiDiagonalAndReturnNull(){
        val game = TicTacToeGameEngine()
        game.initBoard(TicTacToeConfig.TIC_TAC_TOE_SIZE)

        game.forceSetCell(2,0, Player.X)
        game.forceSetCell(1,1, Player.O)
        game.forceSetCell(0,2, Player.X)

        val winner=game.checkWinnerByAntiDiagonal(game.snapshot().board,Player.X)

        assertNull(winner)
    }

}