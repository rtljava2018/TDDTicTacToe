package com.rtllabs.tddtictactoe.domain.usecase

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.entity.GameState
import javax.inject.Inject

class MakeMoveUseCase @Inject constructor(
    private val game: TicTacToeGameEngine) {

    operator fun invoke(row: Int, col: Int): GameState {
        return game.makeMove(row, col)
    }
}