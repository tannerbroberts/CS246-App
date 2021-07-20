package com.example.cs246app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EndCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_end_case);
    }

    public void endIt(View view){
        EditText textBox = findViewById(R.id.response);

        if (textBox.getText().toString().toLowerCase().contains("yes")){
            SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            String NEED_TO_SET_ALARM = "the_recurring_alarm_has_been_set_for_the_pain_journal_reminder";
            editor.putBoolean("isCaseActive", false);
            editor.putBoolean(NEED_TO_SET_ALARM, true);
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