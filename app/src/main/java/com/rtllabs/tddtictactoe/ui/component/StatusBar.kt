package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rtllabs.tddtictactoe.presentation.GameUiState

@Composable
fun StatusBar(state: GameUiState) {
    when (state){
        is GameUiState.GameDraw -> Text(
            text = "It's a Draw!",
            style = MaterialTheme.typography.titleLarge
        )
        is GameUiState.GameInProgress -> Text(
            text = "Current Player: ${state.currentPlayer}",
            style = MaterialTheme.typography.titleLarge
        )
        is GameUiState.GameWon -> Text(
            text = "Winner: ${state.winner}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}