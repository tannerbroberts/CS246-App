package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ReferAFriend extends AppCompatActivity {

    private final static int CONTACT_PICKER = 1;
    String referralPhoneNum;
    String referralFirstName;
    String referralLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        pickContact();

        setContentView(R.layout.activity_refer_afriend);
    }


    public void pickContact()
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case CONTACT_PICKER:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.d("JustChecking", "Failed to pick contact");
            this.finish();
        }
    }
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String mail = null ;
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            Log.d("JustChecking", phoneNo);
            Log.d("JustChecking", name);

            referralPhoneNum = phoneNo;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void referFriend(View v) {
        EditText firstName = findViewById(R.id.referralFirstName);
        EditText lastName = findViewById(R.id.referralLastName);
        referralFirstName = firstName.getText().toString();
        referralLastName = lastName.getText().toString();
        if(referralFirstName.length() < 1 || referralLastName.length() < 1){
            Toast.makeText(this, "Please enter your friend's full name", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
            String leadDocketNumber = "5017370864";
            String messageToLeadDocket = data.getString("firstName", "someone")+ " " + data.getString("lastName", "") + " referred their friend "
                    + referralFirstName + " " + referralLastName + " - " + referralPhoneNum;
            String messageToFriend = "Hey " + referralFirstName+ " here's the info for a good Personal Injury Attorney: (870)246-7070, https://www.levarlaw.com/";

            try {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(referralPhoneNum, null, messageToFriend, null, null);
                smsManager.sendTextMessage(leadDocketNumber, null, messageToLeadDocket, null, null);
                Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "Failed to send!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

