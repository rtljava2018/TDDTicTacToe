package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.rtllabs.tddtictactoe.domain.entity.Player

@Composable
fun GameBoard(board: List<List<Player?>> ,onCellClick: (Int, Int) -> Unit) {
    Column {
        board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Cell(
                        cell
                    ) { onCellClick(rowIndex, colIndex) }
                }
            }
        }
    }
}