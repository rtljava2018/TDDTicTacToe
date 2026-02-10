package com.rtllabs.tddtictactoe.GameScreen

import androidx.compose.runtime.currentComposer
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rtllabs.tddtictactoe.domain.entity.GameState
import com.rtllabs.tddtictactoe.domain.entity.Player
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel
import com.rtllabs.tddtictactoe.ui.component.GameScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GameScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var makeMoveUseCase: MakeMoveUseCase

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = TicTacToeViewModel(makeMoveUseCase)
    }

    @Test
    fun gameScreenInitialStateShouldShowCurrentPlayer() {
        composeTestRule.setContent {
            GameScreen(ticTacToeViewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Current Player: X").assertExists()

    }

    @Test
    fun gameScreenInitialClickCellShouldShowX() {

        composeTestRule.setContent {
            GameScreen(ticTacToeViewModel = viewModel)
        }

        composeTestRule.onAllNodesWithText("").onFirst().performClick()

        composeTestRule.onNodeWithText("X").assertExists()


    }

    @Test
    fun gameScreenRowWinShouldShowWinnerMessage() {

        composeTestRule.setContent {
            GameScreen(ticTacToeViewModel = viewModel)
        }

        viewModel.onCellClicked(0,0) // X
        viewModel.onCellClicked(1,0) // O
        viewModel.onCellClicked(0,1) // X
        viewModel.onCellClicked(1,1) // O
        viewModel.onCellClicked(0,2) // X wins

        composeTestRule.onNodeWithText("Winner: X").assertExists()
    }

    @Test
    fun gameScreenDrawShouldShowDrawMessage() {

        composeTestRule.setContent {
            GameScreen(ticTacToeViewModel = viewModel)
        }

        viewModel.onCellClicked(0,0) // X
        viewModel.onCellClicked(0,1) // O
        viewModel.onCellClicked(0,2) // X
        viewModel.onCellClicked(1,1) // O
        viewModel.onCellClicked(1,0) // X
        viewModel.onCellClicked(1,2) // X
        viewModel.onCellClicked(2,1) // X
        viewModel.onCellClicked(2,0) // X
        viewModel.onCellClicked(2,2) // X

        composeTestRule.onNodeWithText("It's a Draw!").assertExists()
    }
}