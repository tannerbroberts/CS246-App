package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView first = findViewById(R.id.firstName);
        String firstName = first.getText().toString();
        TextView last = findViewById(R.id.lastName);
        String lastName = last.getText().toString();

        if(firstName.length() > 0 && lastName.length() > 0){
            SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putBoolean("isCaseActive", true);
            editor.putString("firstName", firstName.toUpperCase());
            editor.putString("lastName", lastName.toUpperCase());
            editor.apply();
            this.finish();
            Intent intent = new Intent(this, CaseActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Please Enter Your First and Last Name", Toast.LENGTH_SHORT).show();
        }



    }
}