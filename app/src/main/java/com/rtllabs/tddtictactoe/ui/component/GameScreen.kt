package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel

@Composable
fun GameScreen(ticTacToeViewModel: TicTacToeViewModel= hiltViewModel()){
    val state = ticTacToeViewModel.uiState.collectAsStateWithLifecycle()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){

        Text(
            text = "Current Player: ${(state.value as? GameUiState.GameInProgress)?.currentPlayer}",
            style = MaterialTheme.typography.titleLarge
        )
    }

}