package com.rtllabs.tddtictactoe.domain.usecase

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGame
import com.rtllabs.tddtictactoe.domain.entity.GameState

class MakeMoveUseCase(private val game: TicTacToeGame) {

    operator fun invoke(row: Int, col: Int): GameState {
        return game.makeMove(row, col)
    }
}