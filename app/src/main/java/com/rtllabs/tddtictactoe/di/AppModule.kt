package com.rtllabs.tddtictactoe.di

import com.rtllabs.tddtictactoe.domain.engine.GameEngine
import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import com.rtllabs.tddtictactoe.domain.usecase.MakeMoveUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindGameEngine(ticTacToeGameEngine: TicTacToeGameEngine): GameEngine

}