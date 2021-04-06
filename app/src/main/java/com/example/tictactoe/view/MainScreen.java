package com.example.tictactoe.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tictactoe.R;
import com.example.tictactoe.databinding.ActivityMainScreenBinding;
import com.example.tictactoe.model.Player;
import com.example.tictactoe.viewmodel.MyViewModel;

import static com.example.tictactoe.StringHelper.isNullOrEmpty;


public class MainScreen extends AppCompatActivity {

    MyViewModel myViewModel;
    public static final String WINNER = "Winner is ";
    public static final String OK = "Ok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    // method to initialize the dataBinding
    private void initDataBinding()
    {
        ActivityMainScreenBinding activityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.start_game(getResources().getString(R.string.player1_hint), getResources().getString(R.string.player2_hint));
        activityMainScreenBinding.setViewModel(myViewModel);
        myViewModel.getWinner().observe(this, this::getWinner);
    }

    // Method to observe the winner
    public void getWinner(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? "None" : winner.name;
        showWinnerDialog(winnerName);
    }

    // Method to display the dialog with winner name
    private void showWinnerDialog(String winnerName) {
        AlertDialog.Builder winnerDialog = new AlertDialog.Builder(this);
        winnerDialog.setCancelable(false);
        winnerDialog.setMessage(WINNER + winnerName);
        winnerDialog.setPositiveButton(OK, onClickListener).create().show();
    }

    // dismiss the dialog and initiate the game again when ok button clicked on dialog
    DialogInterface.OnClickListener onClickListener = (dialog, which) -> initDataBinding();
}