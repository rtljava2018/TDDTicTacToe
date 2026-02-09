package com.rtllabs.tddtictactoe.domain.usecase

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGame
import com.rtllabs.tddtictactoe.domain.entity.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MakeMoveUseCaseTest {
    private lateinit var game: TicTacToeGame
    private lateinit var useCase: MakeMoveUseCase

    @Before
    fun setup(){
        game = TicTacToeGame()
        useCase = MakeMoveUseCase(game)
    }

    @Test
    fun makeMoveUseCaseShouldReactWithUpdatedGameState(){

        val gameState1=useCase(0,0)//X
        val gameState2=useCase(1,1)//O

        assertEquals(Player.O, gameState1.currentPlayer)
        assertNull(gameState1.winner)
        assertFalse(gameState1.isDraw)
        assertFalse(gameState1.isGameOver)
        assertEquals(Player.X, gameState2.currentPlayer)
    }

    @Test
    fun makeMoveUseCaseShouldNotAllowToMoveOnOccupiedCell(){

        val gameState1=useCase(0,0)//X
        val gameState2=useCase(0,0)//O

        assertEquals(Player.X, gameState1.board[0][0])
        assertEquals(Player.X, gameState2.board[0][0])
        assertEquals(Player.O, gameState1.currentPlayer)
        assertEquals(Player.O, gameState2.currentPlayer)
        assertNull(gameState2.winner)
        assertFalse(gameState2.isDraw)
        assertFalse(gameState2.isGameOver)
    }

    @Test
    fun makeMoveUseCaseShouldDetectRowWin(){

        useCase(0,0)//X
        useCase(1,0)//O
        useCase(0,1)//X
        useCase(1,1)//O
        val gameState=useCase(0,2)//X

        assertEquals(Player.X, gameState.board[0][0])
        assertNotNull(gameState.winner)
        assertFalse(gameState.isDraw)
        assertTrue(gameState.isGameOver)
    }

    @Test
    fun makeMoveUseCaseShouldDetectColumnWin(){

        useCase(0,0)//X
        useCase(1,1)//O
        useCase(1,0)//X
        useCase(2,1)//O
        val gameState=useCase(2,0)//X

        assertEquals(Player.X, gameState.board[0][0])
        assertNotNull(gameState.winner)
        assertFalse(gameState.isDraw)
        assertTrue(gameState.isGameOver)
    }

    @Test
    fun makeMoveUseCaseShouldDetectDiagonalWin(){

        useCase(0,0)//X
        useCase(1,0)//O
        useCase(1,1)//X
        useCase(2,1)//O
        val gameState=useCase(2,2)//X

        assertEquals(Player.X, gameState.board[0][0])
        assertNotNull(gameState.winner)
        assertFalse(gameState.isDraw)
        assertTrue(gameState.isGameOver)
    }

}