package com.example.assignment2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TickerListFragment extends Fragment {

    ListView tickerLV;
    public List<String> tickerList;
    private TickerViewModel tickerViewModel;

    public TickerListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tickerViewModel = TickerViewModel.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        tickerLV = view.findViewById(R.id.tickerLV);

        tickerList = new ArrayList<>();

        tickerList.add("BAC");
        tickerList.add("AAPL");
        tickerList.add("DIS");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, tickerList);

        tickerLV.setAdapter(adapter);

        tickerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTicker = tickerList.get(position);
                tickerViewModel.setSelectedTicker(selectedTicker);
            }
        });

        tickerViewModel.getReceivedTicker().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String ticker) {
                if (tickerList.size() >= 6) {
                    tickerList.remove(5);
                }
                tickerList.add(ticker);
                adapter.notifyDataSetChanged();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}