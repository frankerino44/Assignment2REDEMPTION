package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment tickerListFragment;
    Fragment infoWebFragment;
    private TickerViewModel tickerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();*/

        tickerListFragment = new TickerListFragment();
        infoWebFragment = new InfoWebFragment();

        tickerViewModel = TickerViewModel.getInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.tickerListFCV, tickerListFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.infoWebFCV, infoWebFragment)
                .commit();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            String[] perms = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, perms, 101);
        }
    }
}