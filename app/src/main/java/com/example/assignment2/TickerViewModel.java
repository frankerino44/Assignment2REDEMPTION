package com.example.assignment2;

import android.util.Log;

import androidx.annotation.ArrayRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TickerViewModel extends ViewModel {

    private static TickerViewModel instance;

    private final MutableLiveData<String> selectedTicker = new MutableLiveData<>();
    private final MutableLiveData<String> receivedTicker = new MutableLiveData<>();

    private TickerViewModel() {
    }

    public static TickerViewModel getInstance() {
        if (instance == null) {
            instance = new TickerViewModel();
        }
        return instance;
    }

    public void setSelectedTicker(String ticker) {
        selectedTicker.setValue(ticker);
        Log.i("SELECTED TICKER SET", ticker);
    }

    public LiveData<String> getSelectedTicker() {
        Log.i("SELECTED TICKER GOT", "SELECTED TICKER GOT");
        return selectedTicker;
    }

    public void setReceivedTicker(String ticker) {
        receivedTicker.setValue(ticker);
        Log.i("RECEIVED TICKER SET", ticker);
    }

    public LiveData<String> getReceivedTicker() {
        Log.i("RECEIVED TICKER GOT", "RECEIVED TICKER GOT");
        return receivedTicker;
    }
}