package com.rtllabs.tddtictactoe.domain.usecase

import com.rtllabs.tddtictactoe.domain.engine.GameEngine
import com.rtllabs.tddtictactoe.domain.entity.GameState
import javax.inject.Inject

class MakeMoveUseCase @Inject constructor(
    private val gameEngine: GameEngine
) {

    fun startNewGame(boardSize: Int) : GameState{
        gameEngine.initBoard(boardSize)
        return gameEngine.snapshot()
    }

    operator fun invoke(row: Int, column: Int): GameState {
        return gameEngine.makeMove(row, column)
    }
}