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

                    tickerViewModel.setReceivedTicker(messageBody);
                    Log.i("SMSReceiver", "TickerViewModel instance: " + tickerViewModel);
                }
            }
        }
    }
}