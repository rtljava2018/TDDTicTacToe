package com.rtllabs.tddtictactoe.presenter

import app.cash.turbine.test
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TicTacToeViewModelTest {
    private val viewModel = TicTacToeViewModel()

    @Test
    fun ticTacToeViewModelInitialStateShouldBeInProgress() = runTest{

        viewModel.uiState.test {

            val initialState = awaitItem()

            assert(initialState is GameUiState.GameInProgress)
            cancelAndIgnoreRemainingEvents()
        }
    }




}