package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class CaseActivity extends AppCompatActivity {
    View endCaseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_case);

        endCaseButton = findViewById(R.id.endCase);
        endCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endCase();
            }
        });
    }

    public void openPainJournalActivity(View view) {
        Intent intent = new Intent(this, PainJournalActivity.class);
        startActivity(intent);
    }

    public void endCase(){
        SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putBoolean("isCaseActive", false);
        editor.apply();
        this.finish();
    }



}



