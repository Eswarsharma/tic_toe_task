package com.example.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tictactoe.model.Cell;
import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import static com.example.tictactoe.StringHelper.stringFromNumbers;

public class MyViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void start_game(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    // called when once cell clicked out of 9 cells in GridView
    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            // set the value into the cell. The value might be X or O based on the current player
            cells.put(stringFromNumbers(row,column), game.currentPlayer.value);

            if (game.hasGameEnded())
                game.clearBoard(); // clear the cells
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
