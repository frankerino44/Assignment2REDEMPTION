package com.example.assignment2;

import androidx.annotation.ArrayRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TickerViewModel extends ViewModel {

    private final MutableLiveData<String> selectedTicker = new MutableLiveData<>();

    public void setSelectedTicker(String ticker) {
        selectedTicker.setValue(ticker);
    }

    public LiveData<String> getSelectedTicker() {
        return selectedTicker;
    }
}
