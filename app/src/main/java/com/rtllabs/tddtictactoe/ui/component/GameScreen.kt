package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel

@Composable
fun GameScreen(modifier: Modifier = Modifier, ticTacToeViewModel: TicTacToeViewModel = hiltViewModel()) {
    val uiState= ticTacToeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        StatusBar(uiState.value)
        Spacer(Modifier.height(24.dp))
        when (val state=uiState.value){
            is GameUiState.GameDraw -> GameBoard(state.board){_,_-> }
            is GameUiState.GameInProgress -> GameBoard(state.board){row,col->
                ticTacToeViewModel.onCellClicked(row,col)
            }
            is GameUiState.GameWon -> GameBoard(state.board){_,_-> }
        }

    }
}

