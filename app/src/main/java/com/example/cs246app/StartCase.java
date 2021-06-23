package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class StartCase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_start_case);


    }

    public void startTheCase(View view){

        SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putBoolean("isCaseActive", true);
        editor.apply();
        this.finish();
        Intent intent = new Intent(this, CaseActivity.class);
        startActivity(intent);

    }
}