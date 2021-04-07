package com.example.tictactoe.di;

import com.example.tictactoe.model.Game;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {

    private final String playerOne;
    private final String playerTwo;

    public DaggerModule(String playerOne, String playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Provides
    Game createGame()
    {
        return new Game(playerOne, playerTwo);
    }

}
