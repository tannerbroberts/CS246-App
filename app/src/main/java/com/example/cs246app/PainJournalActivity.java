package com.example.cs246app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Session2Command;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class PainJournalActivity extends AppCompatActivity {
    public static final int CAMERA_ACTION_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pain_journal);
    }

    public void submit(View view) {
        //SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);

        String number = "5017370864";
        //String sms = data.getString("firstName", "someone")+ " " + data.getString("lastName", "") + "'s Pain Journal";
        String smsInput = ((EditText)findViewById(R.id.pain_journal_entry_text)).getText().toString();
        String sms = "My pain: " + smsInput;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            if (sms.equals("My pain: ")) {
                return;
            } else if (sms.length() < 160) {
                smsManager.sendTextMessage(number, null, sms, null, null);
            } else {
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
                }
            }

            Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.pain_journal_entry_text)).setText("");
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPhotoOfInjury(View view) {
        Intent attachPhotoInDefaultSmsApp = new Intent(Intent.ACTION_VIEW);
        attachPhotoInDefaultSmsApp.putExtra("address", "5017370864");
        attachPhotoInDefaultSmsApp.putExtra("sms_body", "This is to document my pain.");
        attachPhotoInDefaultSmsApp.setType("vnd.android-dir/mms-sms");
        try {
            startActivity(attachPhotoInDefaultSmsApp);
            finish();
        } catch (android.content.ActivityNotFoundException  ex) {
            Toast.makeText(this, "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}