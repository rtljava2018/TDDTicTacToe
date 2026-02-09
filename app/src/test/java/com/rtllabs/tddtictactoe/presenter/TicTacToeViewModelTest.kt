package com.rtllabs.tddtictactoe.presenter

import app.cash.turbine.test
import com.rtllabs.tddtictactoe.domain.entity.GameState
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import com.rtllabs.tddtictactoe.presentation.GameUiState
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TicTacToeViewModelTest {

    private val makeMoveUseCase = mockk<MakeMoveUseCase>()
    private val viewModel = TicTacToeViewModel(makeMoveUseCase)

    @Test
    fun ticTacToeViewModelInitialStateShouldBeInProgress() = runTest{

        viewModel.uiState.test {

            val initialState = awaitItem()

            assertTrue(initialState is GameUiState.GameInProgress)
            val inProgress=initialState as GameUiState.GameInProgress
            assertTrue(inProgress.board.flatten().size==9)
            assertEquals(Player.X,inProgress.currentPlayer)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun ticTacToeViewModelOnCellClickedShouldEmitInProgressState() = runTest{

        val fakeState = GameState(
            board = List(3) { List<Player?>(3) { null } },
            currentPlayer = Player.O,
            winner = null,
            isDraw = false,
            isGameOver = false
        )

        every { makeMoveUseCase.invoke(0,0) } returns fakeState

        viewModel.uiState.test {
            skipItems(1) //skip initial state
            viewModel.onCellClicked(0,0)
            val state = awaitItem()

            assertTrue(state is GameUiState.GameInProgress)
            val inProgress=state as GameUiState.GameInProgress
            assertEquals(Player.O,inProgress.currentPlayer)
            cancelAndIgnoreRemainingEvents()
        }
    }




}