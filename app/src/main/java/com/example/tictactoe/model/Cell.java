package com.example.tictactoe.model;

import com.example.tictactoe.StringHelper;

public class Cell {

    public Player player;

    // Constructor which initializes Player class to track current player
    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringHelper.isNullOrEmpty(player.value);
    }
}
