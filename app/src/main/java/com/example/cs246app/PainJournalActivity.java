package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PainJournalActivity extends AppCompatActivity {
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pain_journal);



            submit = findViewById(R.id.pain_journal_send_button);
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
            Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
        }
    }
}