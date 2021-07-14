package com.example.cs246app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
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
        String number = "4352330894";
        String sms = ((EditText)findViewById(R.id.pain_journal_entry_text)).getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            while (sms.length() > 160) {
                String sendNow = sms.substring(0, 160);
                smsManager.sendTextMessage(number, null, sendNow, null, null);
                sms = sms.substring(160);
            }
            Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPhotoOfInjury(View view) {
        Intent attachPhotoInDefaultSmsApp = new Intent(Intent.ACTION_VIEW);
        attachPhotoInDefaultSmsApp.putExtra("address", "4352330894");
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