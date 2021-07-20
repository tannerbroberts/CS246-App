package com.example.cs246app;

import android.content.Context;
import android.telephony.SmsManager;

import java.util.ArrayList;

public class SendPainJournalMessages implements Runnable {
    SmsManager smsManager;
    String number;
    String sms;

    public SendPainJournalMessages(SmsManager smsManager, String number, String sms) {
        this.smsManager = smsManager;
        this.number = number;
        this.sms = sms;
    }

    @Override
    public void run() {
        ArrayList <String> parts = new ArrayList<>();
        do {

            if(sms.length() > 160){
                String sendNow = sms.substring(0, 160);
                parts.add(sendNow);

                sms = sms.substring(160);

            }else{
                parts.add(sms);
                break;
            }

        } while (true);
        for (int i = 0; i < parts.size(); i++) {
            smsManager.sendTextMessage(number, null, parts.get(i), null, null);
            try {
                Thread.sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}