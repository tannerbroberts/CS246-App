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
    Boolean wasEndCaseButtonClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_case);
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        Boolean checkActivity = data.getBoolean("isCaseActive", false);
        if(!checkActivity){
            this.finish();
        }
    }

    public void openPainJournalActivity(View view) {
        Intent intent = new Intent(this, PainJournalActivity.class);
        startActivity(intent);
    }

    public void openRequestedPhotosActivity(View view) {
        Intent intent = new Intent(this, RequestedPhotosActivity.class);
        startActivity(intent);
    }

    public void goToRefer(View view){
        Intent intent = new Intent(this, ReferAFriend.class);
        startActivity(intent);
    }

    public void endCase(View view){

        Intent intent = new Intent(this, EndCaseActivity.class);
        startActivity(intent);

        wasEndCaseButtonClicked = true;
    }
}