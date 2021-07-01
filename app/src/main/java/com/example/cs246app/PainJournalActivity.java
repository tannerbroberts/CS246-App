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
    ClipData clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pain_journal);



//        Button submit = findViewById(R.id.pain_journal_send_button);
//        submit.setOnClickListener(v -> {
//            String number = "4352330894";
//            String sms = ((EditText)findViewById(R.id.pain_journal_entry_text)).getText().toString();
//
//            try {
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(number, null, sms, null, null);
//                Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void submit(View view) {
        String number = "4352330894";
        String sms = ((EditText)findViewById(R.id.pain_journal_entry_text)).getText().toString();

        // TODO: 6/14/21 I still need to be able to add photos, so there's more to add to this function

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, sms, null, null);
            startActivity(new Intent());
            Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPhotoOfInjury(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_ACTION_CODE);
        } else {
            Toast.makeText(this, "There is no app that supports this action", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_ACTION_CODE && resultCode == RESULT_OK && data != null) {
        } else {
            Toast.makeText(this, "The image didn't save", Toast.LENGTH_SHORT).show();
        }

    }
}