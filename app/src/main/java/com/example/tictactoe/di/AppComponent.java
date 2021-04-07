package com.example.tictactoe.di;

import com.example.tictactoe.view.MainScreen;
import com.example.tictactoe.viewmodel.MyViewModel;

import dagger.Component;

@Component(modules = {DaggerModule.class})
public interface AppComponent {

    void inject(MyViewModel myViewModel);

}
