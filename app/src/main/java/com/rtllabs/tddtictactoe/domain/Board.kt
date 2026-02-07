package com.rtllabs.tddtictactoe.domain

class Board {

    val cells: MutableList<MutableList<Player?>>
                    = MutableList(3){ MutableList(3){ null} }

    fun isEmpty(): Boolean {
        return cells.all { row -> row.all { cell -> cell == null } }
    }

    fun isBoardFull(): Boolean {
        return cells.all { row -> row.all { cell -> cell != null } }
    }
}