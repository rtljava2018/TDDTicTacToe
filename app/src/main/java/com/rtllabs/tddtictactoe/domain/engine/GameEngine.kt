package com.rtllabs.tddtictactoe.domain.engine

import com.rtllabs.tddtictactoe.domain.entity.GameState

interface GameEngine {
    fun makeMove(row: Int, column: Int): GameState
    fun initBoard(boardSize: Int)
    fun snapshot(): GameState
}