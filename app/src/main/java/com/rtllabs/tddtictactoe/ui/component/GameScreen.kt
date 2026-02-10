package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel

@Composable
fun GameScreen(modifier: Modifier = Modifier,
               ticTacToeViewModel: TicTacToeViewModel = hiltViewModel()) {

    val uiState= ticTacToeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        StatusBar(uiState.value)
        when (val state=uiState.value){
            is GameUiState.GameDraw -> GameBoard(state.board){_,_-> }
            is GameUiState.GameInProgress -> GameBoard(state.board){row,col->
                ticTacToeViewModel.onCellClicked(row,col)
            }
            is GameUiState.GameWon -> GameBoard(state.board){_,_-> }
        }

    }
}

@Composable
fun StatusBar(state: GameUiState) {
    when (state){
        is GameUiState.GameDraw -> Text(
            text = "It's a Draw!",
        )
        is GameUiState.GameInProgress -> Text(
            text = "Current Player: ${state.currentPlayer}",
        )
        is GameUiState.GameWon -> Text(
            text = "Winner: ${state.winner}",
        )
    }
}

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

@Composable
fun Cell(player: Player?, onCellClick: () -> Unit) {
    val cellValue = player
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(2.dp, Color.Black)
            .clickable(enabled = player == null) {
                onCellClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cellValue?.name ?: "",
        )
    }
}

