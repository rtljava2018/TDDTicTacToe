package com.rtllabs.tddtictactoe.di

import com.rtllabs.tddtictactoe.domain.engine.GameEngine
import com.rtllabs.tddtictactoe.domain.engine.TicTacToeGameEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindGameEngine(ticTacToeGameEngine: TicTacToeGameEngine): GameEngine

}