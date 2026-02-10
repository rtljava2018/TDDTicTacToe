package com.rtllabs.tddtictactoe.di

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGame
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGame(): TicTacToeGame {
        return TicTacToeGame()
    }

    @Provides
    fun provideMakeMoveUseCase(game: TicTacToeGame): MakeMoveUseCase {
        return MakeMoveUseCase(game)
    }

}