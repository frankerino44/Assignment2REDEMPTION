package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private TickerViewModel tickerViewModel;

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        tickerViewModel = TickerViewModel.getInstance();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");

            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String messageBody = smsMessage.getMessageBody();
                    int length = messageBody.length();
                    if (length >= 12) {
                        String ticker = messageBody.substring(9, length-2);
                        if (messageBody.substring(0, 9).matches("Ticker:<<") &&
                                messageBody.substring(length-2, length).matches(">>") &&
                                ticker.matches("[a-zA-Z]+")) {
                            Log.i("IF", "IF");
                            tickerViewModel.setReceivedTicker(ticker.toUpperCase());
                        } else {
                            Toast.makeText(context, "No valid watchlist entry was found.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "No valid watchlist entry was found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}