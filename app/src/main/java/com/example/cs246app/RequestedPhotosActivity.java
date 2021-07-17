package com.example.cs246app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RequestedPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        // set up content view and image preview reference
        setContentView(R.layout.activity_requested_photos);
    }

    public void submit(View view) {
        Intent attachPhotoInDefaultSmsApp = new Intent(Intent.ACTION_VIEW);
        attachPhotoInDefaultSmsApp.putExtra("address", "5017370864");
        attachPhotoInDefaultSmsApp.putExtra("sms_body", "A photo provided at your request");
        attachPhotoInDefaultSmsApp.setType("vnd.android-dir/mms-sms");
        try {
            startActivity(attachPhotoInDefaultSmsApp);
            finish();
        } catch (android.content.ActivityNotFoundException  ex) {
            Toast.makeText(this, "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}