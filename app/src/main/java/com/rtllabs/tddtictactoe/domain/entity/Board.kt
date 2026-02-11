package com.rtllabs.tddtictactoe.domain.entity

class Board {

    private val cells: MutableList<MutableList<Player?>>
                    = MutableList(3){ MutableList(3){ null } }

    fun isEmpty(): Boolean {
        return cells.all { row -> row.all { cell -> cell == null } }
    }

    fun isBoardFull(): Boolean {
        return cells.all { row -> row.all { cell -> cell != null } }
    }

    fun setCells(row: Int, column: Int, player: Player): Boolean {
        if (cells[row][column] != null) return false
        cells[row][column] = player
        return true
    }

    fun getCell(row: Int, column: Int): Player? {
        return cells[row][column]
    }

    fun getAllCells() : List<List<Player?>> {
        return cells.map { it.toList() }
    }

}
