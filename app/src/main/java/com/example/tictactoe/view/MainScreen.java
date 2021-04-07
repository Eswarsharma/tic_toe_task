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

/**
 * Main Activity
 */

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    // method to initialize the dataBinding
    private void initDataBinding()
    {
        ActivityMainScreenBinding activityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.startGame(getResources().getString(R.string.all_playerOne), getResources().getString(R.string.all_playerTwo));
        activityMainScreenBinding.setViewModel(myViewModel);
        myViewModel.getWinner().observe(this, this::getWinner);
    }

    // Method to observe the winner
    private void getWinner(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? getString(R.string.MainActivity_noWinner) : winner.name;
        showWinnerDialog(winnerName);
    }

    // Method to display the dialog with winner name
    private void showWinnerDialog(String winnerName) {
        AlertDialog.Builder winnerDialog = new AlertDialog.Builder(this);
        winnerDialog.setCancelable(false);
        winnerDialog.setMessage(String.format(getString(R.string.MainScreen_WinnerDialog_title), winnerName));
        winnerDialog.setPositiveButton(getResources().getString(R.string.MainActivity_okButton_WinnerDialog), onClickListener).create().show();
    }

    // dismiss the dialog and initiate the game again when ok button clicked on dialog
    private final DialogInterface.OnClickListener onClickListener = (dialog, which) -> initDataBinding();
}