package com.rtllabs.tddtictactoe.GameScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import com.rtllabs.tddtictactoe.presentation.TicTacToeViewModel
import com.rtllabs.tddtictactoe.ui.component.GameScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
    fun gameScreenInitialStateShouldShowCurrentPlayer(){
        composeTestRule.setContent {
            GameScreen(viewModel)
        }

        composeTestRule.onNodeWithText("Current Player: X").assertExists()

    }
}