package com.rtllabs.tddtictactoe.presenter

import app.cash.turbine.test
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TicTacToeViewModelTest {
    private val viewModel = TicTacToeViewModel()

    @Test
    fun ticTacToeViewModelInitialStateShouldBeInProgress() = runTest{

        viewModel.uiState.test {

            val initialState = awaitItem()

            assert(initialState is GameUiState.GameInProgress)
            val inProgress=initialState as GameUiState.GameInProgress
            assert(inProgress.board.flatten().size==9)
            assertEquals(Player.X,inProgress.currentPlayer)
            cancelAndIgnoreRemainingEvents()
        }
    }




}