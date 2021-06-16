package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class CaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_case);
    }

    public void openPainJournalActivity(View view) {
        Intent intent = new Intent(this, PainJournalActivity.class);
        startActivity(intent);
    }
}



