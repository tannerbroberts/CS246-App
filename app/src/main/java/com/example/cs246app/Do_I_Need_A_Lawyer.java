package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Do_I_Need_A_Lawyer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_ineed_alawyer);
    }

    public void call(View view){
        Uri number = Uri.parse("tel:(870)2467070");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        try{
            startActivity(callIntent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(Do_I_Need_A_Lawyer.this, "Your phone can't make this call.", Toast.LENGTH_SHORT).show();
        }
    }
}