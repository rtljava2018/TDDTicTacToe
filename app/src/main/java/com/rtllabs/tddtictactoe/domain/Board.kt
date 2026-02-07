package com.rtllabs.tddtictactoe.domain

class Board {

    private val cells: MutableList<MutableList<Player?>>
                    = MutableList(3){ MutableList(3){ null} }

    fun isEmpty(): Boolean {
        return cells.all { row -> row.all { cell -> cell == null } }
    }

    fun isBoardFull(): Boolean {
        return cells.all { row -> row.all { cell -> cell != null } }
    }

    fun setCells(row: Int, col: Int, x: Player): Boolean {
        if (cells[row][col] != null) return false
        cells[row][col] = x
        return true
    }
}