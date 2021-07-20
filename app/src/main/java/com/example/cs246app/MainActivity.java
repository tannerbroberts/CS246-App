 package com.example.cs246app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //takes away the title from the top of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        Intent setReminder = new Intent(this, MyReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, setReminder, PendingIntent.FLAG_UPDATE_CURRENT);
        SharedPreferences preferences = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        String NEED_TO_SET_ALARM = "the_recurring_alarm_has_been_set_for_the_pain_journal_reminder";

        if (preferences.getBoolean(NEED_TO_SET_ALARM, true)) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, 18);
            cal.set(Calendar.MINUTE, 30);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 86_400_000, broadcast);


            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(NEED_TO_SET_ALARM, false);
            editor.apply();
        }
    }

    public void callAllen(View view){
        Uri number = Uri.parse("tel:(870)2467070");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        try{
            startActivity(callIntent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(MainActivity.this, "Your phone can't make this call.", Toast.LENGTH_SHORT).show();
        }
    }

    public void openLawyerAct(View view){
        Intent intent = new Intent(this, Do_I_Need_A_Lawyer.class);
        startActivity(intent);
    }

    public void openCaseActivity(View view) {

        SharedPreferences data = getSharedPreferences("com.example.cs246app.data", Context.MODE_PRIVATE);
        Boolean isCaseActive = data.getBoolean("isCaseActive", false);
        Intent intent;
        if (isCaseActive) {
            intent = new Intent(this, CaseActivity.class);
        }else{
            intent = new Intent(this, StartCase.class);
        }
        startActivity(intent);
    }

    // This is for granting SMS permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Well... This app won't be worth much to you...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}