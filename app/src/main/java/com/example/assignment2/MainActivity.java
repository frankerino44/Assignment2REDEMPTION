package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment tickerListFragment;
    Fragment infoWebFragment;
    private TickerViewModel tickerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tickerListFragment = new TickerListFragment();
        infoWebFragment = new InfoWebFragment();

        tickerViewModel = new ViewModelProvider(this).get(TickerViewModel.class);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.tickerListFCV, tickerListFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.infoWebFCV, infoWebFragment)
                .commit();
    }
}