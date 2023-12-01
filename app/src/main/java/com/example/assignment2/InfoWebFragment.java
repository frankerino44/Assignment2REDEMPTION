package com.example.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoWebFragment extends Fragment {

    WebView webView;
    private TickerViewModel tickerViewModel;

    public InfoWebFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tickerViewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);

        webView = view.findViewById(R.id.WebView);

        webView.loadUrl("https://seekingalpha.com/");

        tickerViewModel.getSelectedTicker().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String ticker) {
                updateUrl("https://seekingalpha.com/symbol/" + ticker);
            }
        });

        return view;
    }

    public void updateUrl(String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }
}