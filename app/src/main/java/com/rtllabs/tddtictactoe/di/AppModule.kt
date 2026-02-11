package com.rtllabs.tddtictactoe.di

import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGame(): TicTacToeGameEngine {
        return TicTacToeGameEngine()
    }

    @Provides
    fun provideMakeMoveUseCase(game: TicTacToeGameEngine): MakeMoveUseCase {
        return MakeMoveUseCase(game)
    }

}