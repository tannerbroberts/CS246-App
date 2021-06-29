package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EndCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_case);
    }

    public void endIt(View view){
        EditText textBox = findViewById(R.id.response);

        if (textBox.getText().toString().toLowerCase().contains("yes")){
            SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putBoolean("isCaseActive", false);
            editor.apply();
            this.finish();
        }else{

            Toast.makeText(this, "To end enter 'YES'.", Toast.LENGTH_SHORT).show();
        }
    }
    public void exit(View view){
        this.finish();
    }
}